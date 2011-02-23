package org.ocwiki.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.ocwiki.controller.rest.bean.ResourceReportBean;
import org.ocwiki.controller.rest.bean.ResourceReportMapper;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.ResourceReport;
import org.ocwiki.db.dao.ResourceReportDAO;

@Path(ResourceReportService.PATH)
public class ResourceReportService extends AbstractResource {
	public static final String PATH = "/resource_reports";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceReportBean> get(@PathParam("id") long id){
		ResourceReport resourceReport = ResourceReportDAO.fetchByResourceAndUser(id, getUser());
		ResourceReportBean bean = ResourceReportMapper.get().toBean(resourceReport);
		return new ObjectResult<ResourceReportBean>(bean); 		
	}
	
}