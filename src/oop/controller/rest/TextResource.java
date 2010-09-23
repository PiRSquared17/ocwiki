package oop.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.ResourceSearchReport;
import oop.data.Revision;
import oop.data.Test;
import oop.data.TextArticle;
import oop.data.Resource;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;

@Path(TextResource.PATH)
public class TextResource extends AbstractResource {
	
	public static final String PATH = "/TextArticle";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<TextArticle> get(@PathParam("id") long id){
		Resource<TextArticle> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		return new ObjectResult<TextArticle>(resource.getArticle());		
	}
	
	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReport<TextArticle>> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		List<ResourceSearchReport<TextArticle>> listRelatedTest = ArticleDAO
				.fetchRelated(TextArticle.class, resourceID, 0, 5);
		return new ListResult<ResourceSearchReport<TextArticle>>(listRelatedTest);
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
