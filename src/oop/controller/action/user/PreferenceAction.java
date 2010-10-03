package oop.controller.action.user;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.db.dao.UserDAO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PreferenceAction extends AbstractAction {
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private File tempDir;
	private static final String DEST_DIR = "/avatar";
	private static final int SIZE = 100;
	private File destDir;

	@SuppressWarnings("deprecation")
	@Override
	protected void performImpl() throws Exception {
		tempDir = new File(TEMP_DIR);
		if (!tempDir.isDirectory()) {
			throw new ActionException(TEMP_DIR + "khong ton tai");
		}

		String realPath = super.getController().getServletContext()
				.getRealPath(Config.get().getUploadDir() + DEST_DIR);

		destDir = new File(realPath);
		if (!destDir.isDirectory()) {
			throw new ActionException(Config.get().getUploadDir() + DEST_DIR
					+ " khong ton tai");
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(2 * 1024 * 1024); // 2 MB
		diskFileItemFactory.setRepository(tempDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(
				diskFileItemFactory);
		uploadHandler.setSizeMax(2 * 1024 * 1024);

		try {
			List itemsList = uploadHandler.parseRequest(request);
			Iterator itr = itemsList.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();

				if (!item.isFormField() && check(item)) {
					File uploadedFile = new File(destDir, item.getName());
					uploadedFile = resizeAndRenameImage(item);
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
				|| fileExt.equalsIgnoreCase(".gif"))
				&& fileSize <= 2 * 1024 * 1024)
			return true;
		else
			return false;
	}	
	public File resizeAndRenameImage(FileItem originalFileItem){
		try{
		InputStream inputStream = originalFileItem.getInputStream();
		BufferedImage originalImage = ImageIO.read(inputStream);
		
		float x = getRate(originalImage);
		int newHeight =  (int)(originalImage.getHeight()/x);
		int newWidth  =  (int)(originalImage.getWidth()/x);
		
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType()); 
		Graphics2D g = resizedImage.createGraphics(); 
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null); 
		g.dispose();
		// Java chi ho tro ghi dinh dang pnj va jpg nen thong nhat luon la pnj
		String fileName = String.valueOf(getUser().getId());
		ImageIO.write(resizedImage, "png", 
				new File(destDir + "/" + fileName + ".png")); 
		
		File temp = new File(destDir, fileName + ".png");
		return temp;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public float getRate(BufferedImage originalImage){
		float rate = 0;
		int height = originalImage.getHeight();
		int width  = originalImage.getWidth();
		
		if(height <= SIZE && width<=100){
			rate = 1;
		}
		else{		
			if (height >= width)
				rate = height/(float)SIZE;
			else
				rate = width/(float)SIZE;	
		}
		
		return rate;	
	}
}
