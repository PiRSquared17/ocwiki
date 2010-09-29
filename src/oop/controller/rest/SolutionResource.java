package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ObjectResult;
import oop.data.Resource;
import oop.data.TextArticle;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;

@Path(SolutionResource.PATH)
public class SolutionResource extends AbstractResource {

	public static final String PATH = "/Solution";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<TextArticle> get(@PathParam("id") long id){
		Resource<TextArticle> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		return new ObjectResult<TextArticle>(resource.getArticle());
	}
	
	@POST
	public ObjectResult<TextArticle> create(TextArticle text){
		User user = SessionUtils.getUser(getSession());
		ResourceDAO.create(user, TextArticle.class, text);
		return new ObjectResult<TextArticle>(text);
	}

}
