package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ObjectResult;
import oop.data.Revision;
import oop.data.TextArticle;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

@Path("/TextArticle")
public class TextResource extends AbstractResource {
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<TextArticle> get(@PathParam("id") long id){
		Resource<TextArticle> resource = ResourceDAO.fetchById(id, TextArticle.class);
		assertResourceFound(resource);
		return new ObjectResult<TextArticle>(resource.getArticle());		
	}
	
	@POST
	@Path("/{id: \\d+}")
	public ObjectResult<TextArticle> update(
			@PathParam("id") long id,
			Revision<TextArticle> data){
		Resource<TextArticle> resource = getResourceSafe(id, TextArticle.class);
		saveNewRevision(resource, data.getArticle(),data.getSummary(),data.isMinor());
		return new ObjectResult<TextArticle>(resource.getArticle());
	}
}
