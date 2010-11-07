package oop.controller.action.user;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import oop.conf.Config;
import oop.controller.OcwikiApp;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.db.dao.UserDAO;
import oop.util.ImageUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@SuppressWarnings("rawtypes")
public class PreferenceAction extends AbstractAction {
	private static final String DEST_DIR = "/avatar";
	private File destDir;

	@Override
	protected void performImpl() throws IOException, ServletException {
		title("Tuỳ chọn của " + getUser().getFullname());
		
		String realPath = super.getController().getServletContext()
				.getRealPath(Config.get().getUploadDir() + DEST_DIR);

		destDir = new File(realPath);
		if (!destDir.isDirectory()) {
			throw new ActionException(Config.get().getUploadDir() + DEST_DIR
					+ " khong ton tai");
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold((int) Config.get().getMaxAvatarFileBytes());
		diskFileItemFactory.setRepository(OcwikiApp.get().getTemporaryDirectory());
		ServletFileUpload uploadHandler = new ServletFileUpload(
				diskFileItemFactory);
		uploadHandler.setSizeMax(Config.get().getMaxAvatarFileBytes());

		try {
			List itemsList = uploadHandler.parseRequest(request);
			Iterator itr = itemsList.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField() && check(item)) {
					File uploadedFile = saveImageFile(item);
					getUser().setAvatar(uploadedFile.getName());
					UserDAO.persist(getUser());
				}
				else
					this.addError("File Error", "File không hợp lệ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean check(FileItem file) {
		// Get filename
		String fileName = file.getName();
		String fileExt = FilenameUtils.getExtension(fileName);
		long fileSize = file.getSize();
		return (fileExt.equalsIgnoreCase("png")
				|| fileExt.equalsIgnoreCase("jpg")
				|| fileExt.equalsIgnoreCase("jpeg")
				|| fileExt.equalsIgnoreCase("gif"))
				&& fileSize <= Config.get().getMaxAvatarFileBytes();
	}	

	private File saveImageFile(FileItem originalFileItem)
			throws IOException {
		InputStream inputStream = originalFileItem.getInputStream();
		BufferedImage image = ImageIO.read(inputStream);
		image = ImageUtils.ensureMaxSize(image, Config.get()
				.getMaxAvatarDimension(), Config.get()
				.getMaxAvatarDimension());
		// Java chi ho tro ghi dinh dang png va jpg nen thong nhat luon la
		// png
		String fileName = String.valueOf(getUser().getId() + ".png");
		final File imageFile = new File(destDir + "/" + fileName);
		ImageIO.write(image, "png", imageFile);

		BufferedImage thumbnail = ImageUtils.ensureMaxSize(image, Config.get()
				.getAvatarThumbnailDimension(), Config.get()
				.getAvatarThumbnailDimension());
		final File thumbnailFile = new File(destDir + "/thumbnail/" + fileName);
		ImageIO.write(thumbnail, "PNG", thumbnailFile);
		
		return imageFile;
	}
}
