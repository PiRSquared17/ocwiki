package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.RevisionDAO;

@Path(RevisionResource.PATH)
public class RevisionResource extends AbstractResource{
	
	public static final String PATH = "/revisions";
	
	@GET
	@Path("/{revID: \\d+}")
	public ObjectResult<Revision<Article>> get(
			@PathParam("revID") long revID){		
		Revision<Article> revision = (Revision<Article>) RevisionDAO.fetch(revID);
		this.assertResourceFound(revision);
		return new ObjectResult<Revision<Article>>(revision);
	}

	@GET
	@Path("/resource/{resourceID: \\d+}")
	public ListResult<Revision<Article>> listByResource(
			@PathParam("resourceID") long resourceID, 
			@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("30") @QueryParam("size") int size){
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Revision<Article>> revList = RevisionDAO.fetchByResource(resourceID,(page-1)*size,size);
		String nextUrl = null;
		if (revList.size() >= size) {
			nextUrl = PATH + "?page=" + page + "&size=" + size;
		}		
		return new ListResult<Revision<Article>>(revList, nextUrl);
	}	
}
