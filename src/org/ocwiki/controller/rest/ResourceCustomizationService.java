package org.ocwiki.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.ocwiki.controller.rest.bean.ResourceCustomizationBean;
import org.ocwiki.controller.rest.bean.ResourceCustomizationMapper;
import org.ocwiki.controller.rest.bean.ResourceReferenceBean;
import org.ocwiki.controller.rest.bean.ResourceReportBean;
import org.ocwiki.controller.rest.bean.ResourceReportMapper;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.ResourceCustomization;
import org.ocwiki.data.ResourceReport;
import org.ocwiki.db.dao.ResourceCustomizationDAO;
import org.ocwiki.db.dao.ResourceReportDAO;

@Path(ResourceCustomizationService.PATH)
public class ResourceCustomizationService extends AbstractResource {
	public static final  String PATH="/resource_customizations";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceCustomizationBean> get(@PathParam("id") long id){
		ResourceCustomization resourceCustomization = ResourceCustomizationDAO.fetchByResourceAndUser(id, getUser().getId());
		ResourceCustomizationBean bean = ResourceCustomizationMapper.get().toBean(resourceCustomization);
		return new ObjectResult<ResourceCustomizationBean>(bean);
	}
	
	@POST
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceReportBean> update(@PathParam("id") long id, ResourceCustomizationBean data){
		data.setResource(new ResourceReferenceBean(id));
		ResourceCustomization resourceCustomization = ResourceCustomizationMapper.get().toEntity(data);
		resourceCustomization.setUser(getUserNullSafe());
		ResourceCustomizationDAO.persist(resourceCustomization);
		
		ResourceReport resourceReport = ResourceReportDAO.fetchByResourceAndUser(id, getUser().getId());
		ResourceReportBean bean = ResourceReportMapper.get().toBean(resourceReport);
		return new ObjectResult<ResourceReportBean>(bean);
	}
}
