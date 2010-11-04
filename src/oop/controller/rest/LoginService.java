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
import oop.util.FacebookUtils;
import oop.util.OpenIDUtils;
import oop.util.SessionUtils;

@Path(LoginService.PATH)
public class LoginService extends AbstractResource {

	public static final String PATH = "/login";

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<User> login(@FormParam("name") String name,
			@FormParam("password") String password) {
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
	public ObjectResult<User> facebookLogin() {
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
		
		getSession().setAttribute("providerUrl", userSuppliedString);
		if (userSuppliedString!=null){
			Text redirect = new Text(OpenIDUtils.authRequest(userSuppliedString, false, null, getSession()));
			return new ObjectResult<Text>(redirect);
		}else{
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new ErrorResult("empty url")).build());
		}
		
	}
}
