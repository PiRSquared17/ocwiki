package oop.controller.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.User;
import oop.db.dao.UserDAO;

@Path(UserResource.PATH)
public class UserResource extends AbstractResource {

	public static final String PATH = "/users";
	
	@GET
	public ListResult<User> list(
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<User> list = UserDAO.fetch(start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = PATH + "?start=" + (start + size) + "&size=" + size;
		}
		return new ListResult<User>(list, nextUrl);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<User> get(@PathParam("id") long id) {
		User user = UserDAO.fetchById(id);
		assertResourceFound(user);
		return new ObjectResult<User>(user);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<User> update(@PathParam("id") long id, User data) {
		User user = UserDAO.fetchById(id);
		assertResourceFound(user);
		assertVersion(user, data);
		user.setBlocked(data.isBlocked());
		user.setBlockExpiredDate(data.getBlockExpiredDate());
		user.setWarningMessage(data.getWarningMessage());
		user.setWarningExpiredDate(data.getWarningExpiredDate());
		UserDAO.persist(user);
		return new ObjectResult<User>(user);
	}

}
