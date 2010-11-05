package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


import oop.controller.rest.bean.FileBean;
import oop.controller.rest.bean.FileMapper;
import oop.controller.rest.bean.RevisionBean;
import oop.controller.rest.util.ObjectResult;
import oop.data.File;
import oop.data.Resource;
import oop.data.Status;
import oop.db.dao.ArticleDAO;

@Path(FileResource.PATH)
public class FileResource extends AbstractResource {
	
	public static final String PATH = "/files";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<FileBean> get(@PathParam("id") long resourceId){
		Resource<File> fileResource = getResourceSafe(resourceId, File.class);
		File file = fileResource.getArticle();
		FileBean fileBean = FileMapper.get().toBean(file);
		return new ObjectResult<FileBean>(fileBean);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<FileBean> update(@PathParam("id") long resourceId,
			RevisionBean<FileBean> data) throws Exception{
		Resource<File> resource = getResourceSafe(resourceId,File.class);
		if (resource.getStatus() != Status.NEW) {
			validate(data.getArticle());
		}
		WebServiceUtils.assertValid(resource.getArticle().getId() == data.getArticle().getId() + 1, "old version");		
		
		File file = FileMapper.get().toEntity(data.getArticle());	
		file.setFilename(resource.getArticle().getFilename());
		WebServiceUtils.assertValid(!file.getFilename().equals(""), "not upload yet");
		file.setId(0); // coi nó như đối tượng mới
		ArticleDAO.persist(file);

		saveNewRevision(resource, file, data.getSummary(), data.isMinor());
		FileBean bean = FileMapper.get().toBean(resource.getArticle());
		return new ObjectResult<FileBean>(bean);
	}

	private void validate(FileBean file) {
		// TODO Auto-generated method stub
		WebServiceUtils.assertValid(file != null, "File is empty");
	}

}
