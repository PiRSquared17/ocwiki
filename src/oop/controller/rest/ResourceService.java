package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Article;
import oop.data.Resource;
import oop.data.User;
import oop.db.dao.ResourceDAO;


@Path("/resource")
public class ResourceService extends AbstractResource{

	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Resource<Article>> get(@PathParam("id") long id) {
		Resource<Article> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		return new ObjectResult<Resource<Article>>(resource);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<Resource<Article>> update(@PathParam("id") long id, Resource<Article> data){
		Resource<Article> resource = ResourceDAO.fetchById(id);
		this.assertResourceFound(resource);
		resource.setAccessibility(data.getAccessibility());
		ResourceDAO.persist(resource); 
		return new ObjectResult<Resource<Article>>(resource);
	}
}
