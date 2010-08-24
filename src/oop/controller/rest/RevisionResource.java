package oop.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Article;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

@Path("/revisions")
public class RevisionResource extends AbstractResource{
	
	@GET
	@Path("/{revIndex: \\d+}")
	public ObjectResult<Revision<Article>> get(
			@PathParam("revIndex") long revIndex){		
		Revision<Article> revision = (Revision<Article>) RevisionDAO.fetch(revIndex);
		return new ObjectResult<Revision<Article>>(revision);
	}

	@GET
	@Path("/resource/{resourceID: \\d+}/page/{pageIndex: \\d+}")
	public ListResult<Revision<Article>> listByResource(
			@PathParam("resourceID") long resourceID, 
			@PathParam("pageIndex") int pageIndex){
		List<Revision<Article>> revList = RevisionDAO.fetchByResource(resourceID, (pageIndex -1)*50, 50);
		return new ListResult<Revision<Article>>(revList);
	}
	
}
