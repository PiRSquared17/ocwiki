package org.ocwiki.controller.rest.internal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.ocwiki.conf.Config;
import org.ocwiki.controller.rest.AbstractResource;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.UserDAO;
import org.ocwiki.util.SessionUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;

@SuppressWarnings("rawtypes")
@Path("/avatar")
public class AvatarService extends AbstractResource {

	public static int MAX_FILE_SIZE = 10 * 1024 * 1024;

	@POST
	@Path("/")
	@Consumes( { MediaType.MULTIPART_FORM_DATA,
			MediaType.APPLICATION_OCTET_STREAM })
	public JSONObject upload(@PathParam("id") long id) throws Exception {
		User user = UserDAO.fetchById(getUserNullSafe().getId());
		
		String newFileName = "";
		if (getRequest().getContentType().equals(MediaType.MULTIPART_FORM_DATA)) {
			newFileName = uploadMultipart(user);
		} else if (getRequest().getContentType().equals(
				MediaType.APPLICATION_OCTET_STREAM)) {
			newFileName = uploadOctetStream(user);
		} else {
			return new JSONObject().put("error", "unsupported media type");
		}
		user.setAvatar(newFileName);
		UserDAO.persist(user);
		SessionUtils.updateUser(getSession(), user);
		return new JSONObject().put("success", true);
	}
	
	private String uploadOctetStream(User user) throws IOException {
		String filename = getRequest().getHeader("X-File-Name");
		filename = user.getId() + "." + FilenameUtils.getExtension(filename);
		String path = getServletContext().getRealPath(
				Config.get().getUploadDir() + "/avatar/" + filename);
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
	protected String uploadMultipart(User user) throws Exception {
		java.io.File tempDir = new java.io.File(System
				.getProperty("java.io.tmpdir"));
		String realPath = getServletContext().getRealPath(
				Config.get().getUploadDir());
		java.io.File destDir = new java.io.File(realPath);

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(MAX_FILE_SIZE); // 10 MB
		diskFileItemFactory.setRepository(tempDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(
				diskFileItemFactory);
		uploadHandler.setSizeMax(MAX_FILE_SIZE);
		// Parse the HTTP request...
		try {
			List itemsList = uploadHandler.parseRequest(getRequest());
			Iterator itr = itemsList.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField() && check(item)) {
					java.io.File uploadedFile = new java.io.File(destDir, item
							.getName());
					item.write(uploadedFile);
					return uploadedFile.getName();
				} else {
					throw invalidParam("file", "invalid file");
				}
			}
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw invalidParam("file", "file is too big");
		}
		throw invalidParam("file", "no file selected");
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
				|| fileExt.equalsIgnoreCase(".gif") || fileExt
				.equalsIgnoreCase(".svg"))
				&& fileSize <= 10 * 1024 * 1024)
			return true;
		else {
			throw invalidParam("file", "invalid format");
		}
	}

}
