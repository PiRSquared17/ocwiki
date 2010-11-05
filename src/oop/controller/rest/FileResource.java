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
import oop.db.dao.ResourceDAO;

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
		File file = FileMapper.get().toEntity(data.getArticle());
		if(resource.getStatus().equals(Status.NEW)) // luc vua tao file moi
		{
			//kiem tra da upload file chua
			WebServiceUtils.assertValid(!resource.getArticle().getFilename().equals(""), "not upload yet");
			//neu upload roi thi cho phep luu file
			file.setFilename(resource.getArticle().getFilename());
			file.setId(0); // coi nó như đối tượng mới
			ArticleDAO.persist(file);			
			saveNewRevision(resource, file, data.getSummary(), data.isMinor());
			resource.setStatus(Status.NORMAL);// danh dau file da duoc tao
			ResourceDAO.persist(resource);
		}
		else
		{
			validate(data.getArticle());
			// file đã được tạo, bây h up file mới lên hoặc chỉnh sửa thông tin
			if(resource.getStatus().equals(Status.NORMAL))
			{
				if(resource.getArticle().getId() > data.getArticle().getId())
				{
					file.setFilename(resource.getArticle().getFilename());
					file.setId(0); // coi nó như đối tượng mới
					ArticleDAO.persist(file);			
					saveNewRevision(resource, file, data.getSummary(), data.isMinor());
				}
				else
				{
					WebServiceUtils.assertValid(resource.getArticle().getId() == data.getArticle().getId() , "old version");
					file.setId(0); // coi nó như đối tượng mới
					ArticleDAO.persist(file);			
					saveNewRevision(resource, file, data.getSummary(), data.isMinor());
				}
			}
		}
		FileBean bean = FileMapper.get().toBean(resource.getArticle());
		return new ObjectResult<FileBean>(bean);
		
//		if (resource.getStatus() != Status.NEW) {
//			validate(data.getArticle());
//		}
//		if(resource.getArticle().getId() -  data.getArticle().getId() != 0)
//		{
//			WebServiceUtils.assertValid(resource.getArticle().getId() == data.getArticle().getId() + 1, "not upload yet");		
//		}
//		else
//			WebServiceUtils.assertValid(resource.getArticle().getId() == data.getArticle().getId() , "old version");
//		File file = FileMapper.get().toEntity(data.getArticle());	
//		file.setFilename(resource.getArticle().getFilename());
//		WebServiceUtils.assertValid(!file.getFilename().equals(""), "not upload yet");
//		file.setId(0); // coi nó như đối tượng mới
//		ArticleDAO.persist(file);
//		saveNewRevision(resource, file, data.getSummary(), data.isMinor());
//		FileBean bean = FileMapper.get().toBean(resource.getArticle());
//		return new ObjectResult<FileBean>(bean);
	}

	private void validate(FileBean file) {
		// TODO Auto-generated method stub
		WebServiceUtils.assertValid(file != null, "File is empty");
	}

}
