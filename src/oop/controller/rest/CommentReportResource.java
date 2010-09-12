package oop.controller.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import oop.controller.rest.util.ListResult;
import oop.data.CommentReport;
import oop.db.dao.CommentReportDAO;

@Path("/comment_reports")
public class CommentReportResource extends AbstractResource {

	@GET
	@Path("/resource/{resourceId: \\d+}/latest")
	public ListResult<CommentReport> latestList(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<CommentReport> list = CommentReportDAO
				.fetchByResourceAndUserLatest(resourceId, getUser(), start,
						size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/CommentReports/resource/" + resourceId
					+ "/latest?start=" + (start + size) + "&size=" + size;
		}
		return new ListResult<CommentReport>(list, nextUrl);
	}

	@GET
	@Path("/resource/{resourceId: \\d+}")
	public ListResult<CommentReport> list(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<CommentReport> list = CommentReportDAO.fetchByResourceAndUser(
				resourceId, getUser(), start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/CommentReports/resource/" + resourceId + "?start="
					+ (start + size) + "&size=" + size;
		}
		return new ListResult<CommentReport>(list, nextUrl);
	}

}
