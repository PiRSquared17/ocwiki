package org.ocwiki.controller.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.ocwiki.controller.rest.bean.CommentBean;
import org.ocwiki.controller.rest.bean.CommentMapper;
import org.ocwiki.controller.rest.bean.MapperUtils;
import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.Article;
import org.ocwiki.data.Comment;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.CommentDAO;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.RevisionDAO;

@Path(CommentResource.PATH)
public class CommentResource extends AbstractResource {
	
	public static final String PATH = "/comments";

	@GET
	@Path("/resource/{resourceId: \\d+}/latest")
	public ListResult<CommentBean> latestList(
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
		long count = CommentDAO.countByResource(resourceId);
		// convert to beans
		List<CommentBean> beans = MapperUtils.toBeans(list, CommentMapper.get());
		return new ListResult<CommentBean>(beans, nextUrl, count);
	}

	@GET
	@Path("/resource/{resourceId: \\d+}")
	public ListResult<CommentBean> list(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Comment> list = CommentDAO.fetch(resourceId, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = PATH + "/resource/" + resourceId + "?start="
					+ (start + size) + "&size=" + size;
		}
		long count = CommentDAO.countByResource(resourceId);
		// convert to beans
		List<CommentBean> beans = MapperUtils.toBeans(list, CommentMapper.get());
		return new ListResult<CommentBean>(beans, nextUrl, count);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<CommentBean> retrieve(@PathParam("id") long id) {
		Comment comment = CommentDAO.fetch(id);
		assertResourceFound(comment);
		CommentBean bean = CommentMapper.get().toBean(comment);
		return new ObjectResult<CommentBean>(bean);
	}

	@POST
	@Path("/resource/{resourceId: \\d+}")
	public ObjectResult<CommentBean> create(
			@PathParam("resourceId") long resourceId, Comment data) {
		Resource<?> resource = ResourceDAO.fetchById(resourceId);
			assertParamValid(resource != null, "", "resource not found");
		Revision<? extends Article> revision = RevisionDAO
			.fetchLatestByResource(resource.getId());
		Comment comment = new Comment(getUserNullSafe(), new Date(), data
				.getMessage(), resource, revision);
		CommentDAO.persist(comment);
		CommentBean bean = CommentMapper.get().toBean(comment);
		return new ObjectResult<CommentBean>(bean);
	}

}
