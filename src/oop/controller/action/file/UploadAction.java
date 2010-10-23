package oop.controller.action.file;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Namespace;
import oop.db.dao.FileDAO;
import oop.db.dao.NamespaceDAO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadAction extends AbstractAction {

	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private File tempDir;
	private File destDir;

	@SuppressWarnings("deprecation")
	@Override
	protected void performImpl() throws Exception {
		tempDir = new File(TEMP_DIR);
		if (!tempDir.isDirectory()) {
			throw new ActionException(TEMP_DIR + "không tồn tại");
		}

		String realPath = super.getController().getServletContext()
				.getRealPath(Config.get().getUploadDir());

		destDir = new File(realPath);
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
		if (ServletFileUpload.isMultipartContent(request)){
			  // Parse the HTTP request...
			try {
				List itemsList = uploadHandler.parseRequest(request);
				Iterator itr = itemsList.iterator();

				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (!item.isFormField() && check(item)) {
						File uploadedFile = new File(destDir, item.getName());
						item.write(uploadedFile);
						oop.data.File file = new oop.data.File();
						file.setName(uploadedFile.getName());
						file.setNamespace(NamespaceDAO.fetch(Namespace.FILE));
						FileDAO.persist(file);
					}
					else
						this.addError("file", "File không hợp lệ");
				}
			} catch (FileUploadBase.SizeLimitExceededException ex) {
				addError("file", "File to quá!");
				// ex.printStackTrace(); dung bao h bat loi r de do'
			}
		}
		else
			if (getParams().hasParameter("submit")) {
				this.addError("file", "Not Multipart");
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
			addError("file", "Định dạng không hợp lệ!");
			return false;
		}
	}
}
