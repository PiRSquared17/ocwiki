package oop.controller.rest.internal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.conf.Config;
import oop.controller.rest.AbstractResource;
import oop.data.File;
import oop.data.Namespace;
import oop.data.Resource;
import oop.db.dao.FileDAO;
import oop.db.dao.NamespaceDAO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;

@Path("/upload")
public class UploadService extends AbstractResource {

	@POST
	@Path("/{id: \\d+}")
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_OCTET_STREAM})
	public JSONObject upload(@PathParam("id") long id) throws Exception {
		Resource<File> resource = FileDAO.fetchById(id);
		String newFileName;
		if (getRequest().getContentType().equals(MediaType.MULTIPART_FORM_DATA)) {
			newFileName = uploadMultipart();
		} else if (getRequest().getContentType().equals(MediaType.APPLICATION_OCTET_STREAM)) {
			newFileName = uploadOctetStream();
		} else {
			return new JSONObject().put("error", "unsupported media type");
		}
		File newFile = resource.getArticle().copy();
		newFile.setFilename(newFileName);
		saveNewRevision(resource, newFile, "", false);
		return new JSONObject().put("success", true);
	}

	private String uploadOctetStream() throws IOException {
		String filename = getRequest().getHeader("X-File-Name");
		String path = getServletContext().getRealPath(
				Config.get().getUploadDir())
				+ "/" + filename;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			IOUtils.copy(getRequest().getInputStream(), out);
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return filename;
	}

	//
	// copy from UploadAction (modified)
	//

	protected String uploadMultipart() throws Exception {
		java.io.File tempDir = new java.io.File(
				System.getProperty("java.io.tmpdir"));
		String realPath = getServletContext().getRealPath(
				Config.get().getUploadDir());
		java.io.File destDir = new java.io.File(realPath);

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(10 * 1024 * 1024); // 10 MB
		diskFileItemFactory.setRepository(tempDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(
				diskFileItemFactory);
		uploadHandler.setSizeMax(10 * 1024 * 1024);
		// Parse the HTTP request...
		try {
			List itemsList = uploadHandler.parseRequest(getRequest());
			Iterator itr = itemsList.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField() && check(item)) {
					java.io.File uploadedFile = new java.io.File(destDir,
							item.getName());
					item.write(uploadedFile);
					oop.data.File file = new oop.data.File();
					file.setFilename(uploadedFile.getName());
					file.setNamespace(NamespaceDAO.fetch(Namespace.FILE));
					// FileDAO.persist(file);
				} else {
					throw invalidParam("file", "invalid file");
				}
			}
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw invalidParam("file", "file is too big");
			// ex.printStackTrace(); dung bao h bat loi r de do'
		}
		return "xyz";
	}

	public boolean check(FileItem file) {
		// Get filename
		String fileName = file.getName();
		// Get the extension if the file has one
		String fileExt = "";
		int i = -1;
		if ((i = fileName.indexOf(".")) != -1) {
			fileExt = fileName.substring(i);
			fileName = fileName.substring(0, i);
		}
		long fileSize = file.getSize();
		if ((fileExt.equalsIgnoreCase(".png")
				|| fileExt.equalsIgnoreCase(".jpg")
				|| fileExt.equalsIgnoreCase(".gif")
				|| fileExt.equalsIgnoreCase(".svg"))
				&& fileSize <= 10 * 1024 * 1024)
			return true;
		else {
			throw invalidParam("file", "invalid format");
		}
	}
	
}
