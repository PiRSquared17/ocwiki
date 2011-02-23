package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.ocwiki.controller.rest.bean.MapperUtils;
import org.ocwiki.controller.rest.bean.TopicReportBean;
import org.ocwiki.controller.rest.bean.TopicReportMapper;
import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.TopicReport;
import org.ocwiki.db.dao.TopicReportDAO;

@Path(TopicReportResource.PATH)
public class TopicReportResource extends AbstractResource {

	public static final String PATH = "/topic_reports";

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<TopicReportBean> retrieve(
			@PathParam("id") long resourceId) {
		TopicReport report = TopicReportDAO.fetchById(resourceId);
		assertResourceFound(report);
		TopicReportBean bean = TopicReportMapper.get().toBean(report);
		return new ObjectResult<TopicReportBean>(bean);
	}

	@GET
	@Path("/{id: \\d+}/children")
	public ListResult<TopicReportBean> listChildren(
			@PathParam("id") long parentId) {
		List<TopicReport> children = TopicReportDAO.fetchChildren(parentId);
		List<TopicReportBean> beans = MapperUtils.toBeans(children,
				TopicReportMapper.get());
		return new ListResult<TopicReportBean>(beans);
	}

}
