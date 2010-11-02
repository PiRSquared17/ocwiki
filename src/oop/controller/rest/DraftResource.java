package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import oop.controller.rest.util.ObjectResult;
import oop.data.Text;
import oop.data.User;

@Path(DraftResource.PATH)
public class DraftResource extends AbstractResource {

	public static final String PATH = "/draft";
	
	@GET
	public ObjectResult<Text> retrieve() {
		User user = getUserNullSafe();
		return new ObjectResult<Text>(new Text(user.getDraft()));
	}
	
	@PUT
	public void update(Text draft) {
		User user = getUserNullSafe();
		user.setDraft(draft.getText());
	}
	
}
