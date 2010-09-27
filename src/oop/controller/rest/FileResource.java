package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import oop.controller.rest.util.ObjectResult;
import oop.data.File;
import oop.data.Revision;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

@Path(FileResource.PATH)
public class FileResource extends AbstractResource{
	
	public static final String PATH = "/file";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<File> get(@PathParam("id") long id){
		Resource<File> file = ResourceDAO.fetchById(id);
		assertResourceFound(file);
		return new ObjectResult<File>(file.getArticle());	
	}
	
	@POST
	public ObjectResult<File> upload(Revision<File> data){
		return null;
	}
}
