package oop.controller.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.bean.MapperUtils;
import oop.controller.rest.bean.ResourceBean;
import oop.controller.rest.bean.ResourceMapper;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Article;
import oop.data.Group;
import oop.data.Resource;
import oop.data.Status;
import oop.db.dao.ResourceDAO;

@Path(ResourceService.PATH)
public class ResourceService extends AbstractResource {
	
	public static final String PATH = "/resources";
	
	@POST
	public ObjectResult<ResourceBean> create(ResourceBean bean) {
		assertParamValid("data must not be null", bean != null);
		assertParamValid("article must not be null", bean.getArticle() != null);
		Resource<Article> resource = ResourceMapper.get().toEntity(bean);
		resource.setId(0);
		resource.setVersion(0);
		resource.setStatus(Status.NEW);
		resource.setAuthor(getUserNullSafe());
		resource.setCreateDate(new Date());
		ResourceDAO.persist(resource);
		bean = ResourceMapper.get().toBean(resource);
		return new ObjectResult<ResourceBean>(bean);
	}
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<ResourceBean> get(@PathParam("id") long id) {
		Resource<Article> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		return new ObjectResult<ResourceBean>(ResourceMapper.get().toBean(
				resource));
	}
	
	@GET
	@Path("/namespace/{namespaceId: \\d+}")
	public ListResult<ResourceBean> resourceList(
			@PathParam("namespaceId") long namespaceID, 
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size){
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Resource<Article>> resList = ResourceDAO.fetchByNamespace(
				namespaceID, start, size);
		String nextUrl = null;
		if (resList.size() >= size) {
			nextUrl = PATH + "/namespace/" + namespaceID + "?start="
					+ (start + size) + "&size=" + size;
		}
		List<ResourceBean> beans = MapperUtils.toBeans(resList, ResourceMapper
				.get());
		return new ListResult<ResourceBean>(beans, nextUrl);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public <T extends Article> ObjectResult<ResourceBean> update(
			@PathParam("id") long id, ResourceBean data) {
		requireGroup(Group.ADMIN);
		Resource<Article> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		assertVersion(resource, data);
		resource.setAccessibility(data.getAccessibility());
		resource.setStatus(data.getStatus());
		ResourceDAO.persist(resource);
		return new ObjectResult<ResourceBean>(ResourceMapper.get().toBean(
				resource));
	}
	
}
