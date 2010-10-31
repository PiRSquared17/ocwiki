package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ObjectResult;
import oop.data.Article;
import oop.data.Resource;
import oop.data.ResourceCustomization;
import oop.data.ResourceLike;
import oop.data.ResourceTodo;
import oop.data.User;
import oop.db.dao.ResourceCustomizationDAO;
import oop.db.dao.ResourceDAO;


@Path(ResourceCustomizationResource.PATH)
public class ResourceCustomizationResource extends AbstractResource {
	public static final  String PATH="/resource_customizations";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceCustomization> get(@PathParam("id") long id){
		ResourceCustomization resourcereport = ResourceCustomizationDAO.fetchByResourceAndUser(id, getUser().getId());
		return new ObjectResult<ResourceCustomization>(resourcereport);
	}
	
	@POST
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceCustomization> update(@PathParam("id") long id,ResourceCustomization data){
		Resource<? extends Article> resource = ResourceDAO.fetchById(data.getResource().getId());
		User user = getUser();
		ResourceCustomization resourcecustomization = ResourceCustomizationDAO.fetchByResourceAndUser(resource.getId(), user.getId());
		int level;
		ResourceLike resourcelike;
		ResourceTodo resourcetodo;
		//ResourceCustomization resourcecustomization = new ResourceCustomization(
		//			resource,user,level,resourcelike,resourcetodo);
		if (resourcecustomization == null) {
			level = data.getLevel() == -1 ? 0 : data.getLevel();
			resourcelike = data.getLike() == null ? ResourceLike.NORMAL : data.getLike();
			resourcetodo = data.getTodo() == null ? ResourceTodo.NORMAL : data.getTodo();
			resourcecustomization = new ResourceCustomization(
				resource,user,level,resourcelike,resourcetodo);
		}
		else{
			level = data.getLevel() == -1 ? resourcecustomization.getLevel() : data.getLevel();
			resourcelike = data.getLike() == null ? resourcecustomization.getLike() : data.getLike();
			resourcetodo = data.getTodo() == null ? resourcecustomization.getTodo() : data.getTodo();
			resourcecustomization.setLevel(level);
			resourcecustomization.setLike(resourcelike);
			resourcecustomization.setTodo(resourcetodo);
		}
		ResourceCustomization res = ResourceCustomizationDAO.persist(resourcecustomization);
		return new ObjectResult<ResourceCustomization>(res);
	}
}
