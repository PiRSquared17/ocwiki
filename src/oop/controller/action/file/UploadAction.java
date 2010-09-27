package oop.controller.action.file;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.db.dao.FileDAO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("unchecked")
public class UploadAction extends AbstractAction {

	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private File tempDir;
	private static final String DEST_DIR = "/file";
	private File destDir;

	@Override
	protected void performImpl() throws Exception {
		tempDir = new File(TEMP_DIR);
		if (!tempDir.isDirectory()) {
			throw new ActionException(TEMP_DIR + "không tồn tại");
		}

		String realPath = super.getController().getServletContext()
				.getRealPath(Config.get().getUploadDir() + DEST_DIR);

		destDir = new File(realPath);
		if (!destDir.isDirectory()) {
			throw new ActionException(Config.get().getUploadDir() + DEST_DIR
					+ " không tồn tại");
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(10 * 1024 * 1024); // 10 MB
		diskFileItemFactory.setRepository(tempDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(
				diskFileItemFactory);
		uploadHandler.setSizeMax(10 * 1024 * 1024);

		try {
			List itemsList = uploadHandler.parseRequest(request);
			Iterator itr = itemsList.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();

				if (!item.isFormField()) {
					File uploadedFile = new File(destDir, item.getName());
					oop.data.File file = new oop.data.File();
					file.setName(uploadedFile.getName());
					FileDAO.persist(file);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
