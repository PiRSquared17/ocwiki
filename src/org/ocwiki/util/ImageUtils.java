package org.ocwiki.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class ImageUtils {

	private ImageUtils() {
	}

	public static BufferedImage ensureMaxSize(BufferedImage image,
			int maxWidth, int maxHeight) {
		if (image.getWidth() <= maxWidth && image.getHeight() <= maxHeight) {
			return image;
		}
		float rate = 0;
		if (image.getHeight() >= image.getWidth())
			rate = maxHeight / (float) image.getHeight();
		else
			rate = maxWidth / (float) image.getWidth();

		int newHeight = (int) (image.getHeight() * rate);
		int newWidth = (int) (image.getWidth() * rate);

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight,
				image.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return resizedImage;
	}

}
