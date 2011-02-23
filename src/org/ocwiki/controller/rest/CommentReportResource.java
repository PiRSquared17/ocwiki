package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.ocwiki.controller.rest.bean.CommentReportBean;
import org.ocwiki.controller.rest.bean.CommentReportMapper;
import org.ocwiki.controller.rest.bean.MapperUtils;
import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.data.CommentReport;
import org.ocwiki.db.dao.CommentDAO;
import org.ocwiki.db.dao.CommentReportDAO;

@Path(CommentReportResource.PATH)
public class CommentReportResource extends AbstractResource {

	public static final String PATH = "/comment_reports";
	
	@GET
	@Path("/resource/{resourceId: \\d+}/latest")
	public ListResult<CommentReportBean> latestList(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<CommentReport> list = CommentReportDAO
				.fetchByResourceAndUserLatest(resourceId, getUser(), start,
						size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = PATH + "/resource/" + resourceId
					+ "/latest?start=" + (start + size) + "&size=" + size;
		}
		long count = CommentDAO.countByResource(resourceId);
		// convert to bean
		List<CommentReportBean> beans = MapperUtils.toBeans(list,
				CommentReportMapper.get());
		return new ListResult<CommentReportBean>(beans, nextUrl, count);
	}

	@GET
	@Path("/resource/{resourceId: \\d+}")
	public ListResult<CommentReportBean> list(
			@PathParam("resourceId") long resourceId,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<CommentReport> list = CommentReportDAO.fetchByResourceAndUser(
				resourceId, getUser(), start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = PATH + "/resource/" + resourceId + "?start="
					+ (start + size) + "&size=" + size;
		}
		long count = CommentDAO.countByResource(resourceId);
		// convert to bean
		List<CommentReportBean> beans = MapperUtils.toBeans(list,
				CommentReportMapper.get());
		return new ListResult<CommentReportBean>(beans, nextUrl, count);
	}

}
