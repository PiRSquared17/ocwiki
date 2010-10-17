package oop.controller.rest.internal;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import oop.conf.Config;
import oop.controller.action.ActionException;
import oop.controller.rest.AbstractResource;
import oop.data.Namespace;
import oop.db.dao.NamespaceDAO;

@Path("/upload")
public class UploadService extends AbstractResource {

	@POST
	@Path("/{id: \\d+}")
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_OCTET_STREAM})
	public JSONObject upload(@PathParam("id") long id) throws JSONException {
		performImpl();
		return new JSONObject().put("success", true);
	}

	//
	// copy from UploadAction (modified)
	//
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private java.io.File tempDir;
	private java.io.File destDir;

	protected void performImpl() {
		tempDir = new java.io.File(TEMP_DIR);
		if (!tempDir.isDirectory()) {
			throw new ActionException(TEMP_DIR + "không tồn tại");
		}

		String realPath = getServletContext().getRealPath(
				Config.get().getUploadDir());

		destDir = new java.io.File(realPath);
		if (!destDir.isDirectory()) {
			throw new ActionException(Config.get().getUploadDir()
					+ " không tồn tại");
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(10 * 1024 * 1024); // 10 MB
		diskFileItemFactory.setRepository(tempDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(
				diskFileItemFactory);
		uploadHandler.setSizeMax(10 * 1024 * 1024);
		if (ServletFileUpload.isMultipartContent(getRequest())){
			  // Parse the HTTP request...
			try {
				List itemsList = uploadHandler.parseRequest(getRequest());
				Iterator itr = itemsList.iterator();

				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (!item.isFormField() && check(item)) {
						java.io.File uploadedFile = new java.io.File(destDir, item.getName());
						item.write(uploadedFile);
						oop.data.File file = new oop.data.File();
						file.setName(uploadedFile.getName());
						file.setNamespace(NamespaceDAO.fetch(Namespace.FILE));
						//FileDAO.persist(file);
					}
					else {
						throw invalidParam("file", "invalid file");
					}
				}
			} catch (FileUploadBase.SizeLimitExceededException ex) {
				throw invalidParam("file", "file is too big");
				// ex.printStackTrace(); dung bao h bat loi r de do'
			} catch (Exception e) {
				e.printStackTrace();
				throw new WebApplicationException(e);
			}
		}
		else
			if (getParams().hasParameter("submit")) {
				throw invalidParam("file", "Not Multipart");
			}
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
