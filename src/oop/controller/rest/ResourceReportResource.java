package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ObjectResult;
import oop.data.ResourceReport;
import oop.db.dao.ResourceReportDAO;

@Path(ResourceReportResource.PATH)
public class ResourceReportResource extends AbstractResource {
	public static final String PATH = "/resource_reports";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceReport> get(@PathParam("id") long id){
		ResourceReport resourcereport = ResourceReportDAO.fetchByResourceAndUser(id, getUser());
		
		return new ObjectResult<ResourceReport>(resourcereport); 		
	}
}
