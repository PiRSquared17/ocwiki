package oop.controller.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.CommentStatus;
import oop.data.Resource;
import oop.data.Revision;
import oop.db.dao.CommentDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;

@Path("/comments")
public class CommentResource extends AbstractResource {

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
			nextUrl = "/comments/resource/" + resourceId + "/latest?start="
					+ (start + size) + "&size=" + size;
		}
		return new ListResult<Comment>(list, nextUrl);
	}

	@GET
	@Path("/resource/{resourceId: \\d+}")
	public ListResult<Comment> list(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Comment> list = CommentDAO.fetch(resourceId, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/comments/resource/" + resourceId + "?start="
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Comment> create(
			@FormParam("resourceId") long resourceId,
			@FormParam("revisionId") long revisionId,
			@FormParam("message") String message) {
		Resource<?> resource = ResourceDAO.fetchById(resourceId);
		assertParamValid(resource != null, "", "resource not found");
		Revision<?> revision = RevisionDAO.fetch(revisionId);
		assertParamValid(revision != null, "", "revision not found");
		Comment comment = new Comment(getUser(), new Date(), message, resource,
				revision, CommentStatus.NORMAL);
		CommentDAO.persist(comment);
		return new ObjectResult<Comment>(comment);
	}

	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Comment> update(@PathParam("id") long id,
			@FormParam("message") String message,
			@FormParam("status") CommentStatus status) {
		Comment comment = CommentDAO.fetch(id);
		comment.setMessage(message);
		comment.setStatus(status);
		CommentDAO.persist(comment);
		return new ObjectResult<Comment>(comment);
	}

}
