package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import oop.controller.rest.util.ErrorResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;

@Path("/login")
public class LoginService extends AbstractResource {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<User> login(
			@FormParam("name") String name,
			@FormParam("password") String password) {
		User user = UserDAO.fetchByUsername(name);
		if (user == null) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
					.entity(new ErrorResult("invalid name")).build());
		}
		if (user.isBlocked()) {
			throw new WebApplicationException(Response.status(Status.FORBIDDEN)
					.entity(new ErrorResult("account blocked")).build());
		}
		if (user.matchPassword(password)) {
			SessionUtils.setUser(getSession(), user);
			return new ObjectResult<User>(user);
		} else {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
					.entity(new ErrorResult("invalid password")).build());
		}
	}
	
}
