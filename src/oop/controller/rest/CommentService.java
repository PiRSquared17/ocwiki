package oop.controller.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.beans.CommentBean;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.User;
import oop.db.dao.CommentDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;
import oop.db.dao.UserDAO;

@Path("/comments")
public class CommentService extends AbstractResource {

	@GET
	@Path("/resource/{resourceId: \\d+}/latest")
	public ListResult<Comment> latestList(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("start") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Comment> list = CommentDAO.fetchLatest(resourceId, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/comments/latest?start=" + (start + size) + "&size="
					+ size;
		}
		return new ListResult<Comment>(list, nextUrl);
	}

	@GET
	@Path("/resource/{resourceId: \\d+}")
	public ListResult<Comment> list(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("start") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Comment> list = CommentDAO.fetch(resourceId, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/comments/latest?start=" + (start + size) + "&size="
					+ size;
		}
		return new ListResult<Comment>(list, nextUrl);
	}

	@GET
	@Path("{id: \\d+}")
	public ObjectResult<Comment> retrieve(@PathParam("id") long id) {
		Comment comment = CommentDAO.fetch(id);
		assertResourceFound(comment);
		return new ObjectResult<Comment>(comment);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<Comment> create(CommentBean data) {
		User user = UserDAO.fetchById(data.userId);
		assertParamValid(user != null, "", "user not found");
		Resource<?> resource = ResourceDAO.fetchById(data.resourceId);
		assertParamValid(resource != null, "", "resource not found");
		Revision<?> revision = RevisionDAO.fetch(data.revisionId);
		assertParamValid(revision != null, "", "revision not found");
		Comment comment = new Comment(user, new Date(), data.message, resource,
				revision, data.status);
		CommentDAO.persist(comment);
		return new ObjectResult<Comment>(comment);
	}

	@PUT
	@Path("{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<Comment> update(@PathParam("id") long id,
			CommentBean data) {
		Comment comment = CommentDAO.fetch(id);
		assertVersion(comment, data);
		comment.setMessage(data.message);
		comment.setStatus(data.status);
		CommentDAO.persist(comment);
		return new ObjectResult<Comment>(comment);
	}

}
