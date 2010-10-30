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
import oop.db.dao.FileDAO;

@Path(FileResource.PATH)
public class FileResource extends AbstractResource {
	
	public static final String PATH = "/files";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<File> get(@PathParam("id") long id){
		Resource<File> fileResource = FileDAO.fetchById(id);
		File file = fileResource.getArticle();
		return new ObjectResult<File>(file);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<File> update(@PathParam("id") long id, File data) {
		Resource<File> fileResource = FileDAO.fetchById(id);
		File file = fileResource.getArticle();
		assertResourceFound(file);
		file.setOriginalSource(data.getOriginalSource());
		file.setAuthor(data.getAuthor());
		file.setAdditionalInfo(data.getAdditionalInfo());
		file.setLicense(data.getLicense());
		file.setDateOfWork(data.getDateOfWork());
		FileDAO.persist(file);
		return new ObjectResult<File>(file);
	}

}
