package oop.controller.rest;

import java.util.List;

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
	
	@GET
	@Path("/{namespaceID: \\d+}")
	public ListResult<Resource<Article>> resourceList(
			@PathParam("namespaceID") long namespaceID, 
			@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("30") @QueryParam("size") int size){
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Resource<Article>> resList = ResourceDAO.fetchByNamespace(namespaceID,(page-1)*size, size);
		String nextUrl = null;
		if (resList.size() >= size) {
			nextUrl = "/page=" + page + "&size=" + size;
		}		
		return new ListResult<Resource<Article>>(resList, nextUrl);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<Resource<Article>> update(@PathParam("id") long id, Resource<Article> data){
		Resource<Article> resource = ResourceDAO.fetchById(id);
		this.assertResourceFound(resource);
		resource.setAccessibility(data.getAccessibility());
		resource.setStatus(data.getStatus());
		ResourceDAO.persist(resource); 
		return new ObjectResult<Resource<Article>>(resource);
	}
}
