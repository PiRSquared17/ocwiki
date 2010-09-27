package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.data.File;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

import com.sun.jersey.multipart.FormDataMultiPart;

@Path(FileResource.PATH)
public class FileResource extends AbstractResource{
	
	public static final String PATH = "/files";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<File> get(@PathParam("id") long id){
		Resource<File> file = ResourceDAO.fetchById(id);
		assertResourceFound(file);
		return new ObjectResult<File>(file.getArticle());	
	}
	
	@POST
	@Path("/{id: \\d+}/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ObjectResult<File> upload(@PathParam("id") long id,
			FormDataMultiPart form){
		System.out.println(form.getField("file").getMediaType());
		return null;
	}
}
