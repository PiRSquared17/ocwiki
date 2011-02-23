package org.ocwiki.controller.rest;

import java.io.IOException;
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

import org.ocwiki.controller.rest.bean.UserBean;
import org.ocwiki.controller.rest.bean.UserMapper;
import org.ocwiki.controller.rest.util.ErrorResult;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.FacebookAccount;
import org.ocwiki.data.Text;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.FacebookAccountDAO;
import org.ocwiki.db.dao.UserDAO;
import org.ocwiki.util.BlockedUserException;
import org.ocwiki.util.FacebookUtils;
import org.ocwiki.util.OpenIDUtils;
import org.ocwiki.util.SessionUtils;

import org.openid4java.consumer.ConsumerException;

@Path(LoginService.PATH)
public class LoginService extends AbstractResource {

	public static final String PATH = "/login";

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<UserBean> login(@FormParam("name") String name,
			@FormParam("password") String password) {
		User user = UserDAO.fetchByUsername(name);
		if (user == null) {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(new ErrorResult("invalid name"))
					.build());
		}
		if (user.matchPassword(password)) {
			SessionUtils.login(getSession(), user);
			UserBean bean = UserMapper.get().toBean(user);
			return new ObjectResult<UserBean>(bean);
		} else {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new ErrorResult("invalid password")).build());
		}
	}

	@Path("/facebook")
	@GET
	public ObjectResult<UserBean> facebookLogin() {
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
		UserBean bean = UserMapper.get().toBean(account.getUser());
		return new ObjectResult<UserBean>(bean);
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
