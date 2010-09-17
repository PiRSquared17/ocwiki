package oop.controller.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Article;
import oop.data.Comment;
import oop.data.Resource;
import oop.data.Revision;
import oop.db.dao.CommentDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;

@Path(CommentResource.PATH)
public class CommentResource extends AbstractResource {
	
	public static final String PATH = "/comments";

	@GET
	@Path("/resource/{resourceId: \\d+}/latest")
	public ListResult<Comment> latestList(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Comment> list = CommentDAO.fetchLatest(resourceId, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = PATH + "/resource/" + resourceId + "/latest?start="
					+ (start + size) + "&size=" + size;
		}
		return new ListResult<Comment>(list, nextUrl);
	}

	@GET
	@Path("/resource/{resourceId: \\d+}")
	public ListResult<Comment> list(@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Comment> list = CommentDAO.fetch(resourceId, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = PATH + "/resource/" + resourceId + "?start="
					+ (start + size) + "&size=" + size;
		}
		return new ListResult<Comment>(list, nextUrl);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Comment> retrieve(@PathParam("id") long id) {
		Comment comment = CommentDAO.fetch(id);
		assertResourceFound(comment);
		return new ObjectResult<Comment>(comment);
	}

	@POST
	@Path("/resource/{resourceId: \\d+}")
	public ObjectResult<Comment> create(@PathParam("resourceId") long resourceId, Comment data) {
		Resource<?> resource = ResourceDAO.fetchById(resourceId);
			assertParamValid(resource != null, "", "resource not found");
		Revision<? extends Article> revision = RevisionDAO
			.fetchLatestByResource(resource.getId());
		Comment comment = new Comment(getUser(), new Date(), data.getMessage(),
				resource, revision);
		CommentDAO.persist(comment);
		return new ObjectResult<Comment>(comment);
	}

}
