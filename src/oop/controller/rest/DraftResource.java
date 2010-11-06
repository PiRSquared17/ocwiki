package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.data.Text;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;

@Path(DraftResource.PATH)
public class DraftResource extends AbstractResource {

	public static final String PATH = "/draft";
	
	@GET
	public ObjectResult<Text> retrieve() {
		User user = getUserNullSafe();
		return new ObjectResult<Text>(new Text(user.getDraft()));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Text draft) throws Exception {
		long id = getUserNullSafe().getId();
		User user = UserDAO.fetchById(id);
		user.setDraft(draft.getText());
		UserDAO.persist(user);
		SessionUtils.login(getSession(), user);
	}
	
}
