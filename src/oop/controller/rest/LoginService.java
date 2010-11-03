package oop.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.ax.FetchRequest;

import oop.controller.action.ActionException;
import oop.controller.action.ActionUtil;
import oop.controller.rest.util.ErrorResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.FacebookAccount;
import oop.data.Text;
import oop.data.User;
import oop.db.dao.FacebookAccountDAO;
import oop.db.dao.UserDAO;
import oop.util.BlockedUserException;
import oop.util.FacebookUtils;
import oop.util.SessionUtils;

@Path(LoginService.PATH)
public class LoginService extends AbstractResource {

	public static final String PATH = "/login";
	private ConsumerManager manager;
    final private String yahooEndpoint = "https://me.yahoo.com"; 
    final private String googleEndpoint = "https://www.google.com"; 

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<User> login(@FormParam("name") String name,
			@FormParam("password") String password) throws BlockedUserException {
		User user = UserDAO.fetchByUsername(name);
		if (user == null) {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(new ErrorResult("invalid name"))
					.build());
		}
		if (user.matchPassword(password)) {
			SessionUtils.login(getSession(), user);
			return new ObjectResult<User>(user);
		} else {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new ErrorResult("invalid password")).build());
		}
	}

	@Path("/facebook")
	@GET
	public ObjectResult<User> facebookLogin() throws BlockedUserException {
		SortedMap<String, String> facebook = FacebookUtils
				.readFacebookCookie(getRequest());
		if (facebook == null) {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new ErrorResult("no cookies")).build());
		}
		String uid = facebook.get("uid");
		FacebookAccount account = FacebookAccountDAO.fetchByUid(uid);
		if (account == null) {
			account = FacebookUtils.register(uid);
		}
		SessionUtils.login(getRequest().getSession(), account
				.getUser());
		return new ObjectResult<User>(account.getUser());
	}
	
	@Path("/openid")
	@GET
	public ObjectResult<Text> openIDLogin(@QueryParam("userSuppliedOpenIDUrl") String userSuppliedString) 
		throws BlockedUserException, ConsumerException, IOException {
		
		manager = new ConsumerManager();
		if (userSuppliedString!=null){
			return new ObjectResult<Text>(new Text(authRequest(userSuppliedString)));
		}else{
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new ErrorResult("empty url")).build());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	// --- placing the authentication request ---
	public String authRequest(String userSuppliedString)
			throws IOException {
		try {
			// Sau nay mun dang nhap xong tra ve j thi sua o day
			String returnToUrl = ActionUtil.getActionURL("user.login.openid.verification");
			List discoveries = manager.discover(userSuppliedString);
			DiscoveryInformation discovered = manager.associate(discoveries);
			getSession().setAttribute("openid-disc", discovered);
			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

            FetchRequest fetch = FetchRequest.createFetchRequest(); 
            if (userSuppliedString.startsWith(googleEndpoint)) { 
                    fetch.addAttribute("email", "http://axschema.org/contact/email", true); 
                    fetch.addAttribute("firstname", "http://axschema.org/namePerson/first", true); 
                    fetch.addAttribute("lastname", "http://axschema.org/namePerson/last", true); 
            } 
            else if (userSuppliedString.startsWith(yahooEndpoint)) { 
                    fetch.addAttribute("email", "http://axschema.org/contact/email", true); 
                    fetch.addAttribute("fullname", "http://axschema.org/namePerson", true); 
            } 
            else { //works for myOpenID 
                    fetch.addAttribute("fullname", "http://schema.openid.net/namePerson", true); 
                    fetch.addAttribute("email", "http://schema.openid.net/contact/email", true); 
            } 
            authReq.addExtension(fetch);
            getSession().setAttribute("OIDManager", manager);
			
			return authReq.getDestinationUrl(true);
		
		} catch (OpenIDException e) {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new ErrorResult("connection error")).build());
		}
	}	

}
