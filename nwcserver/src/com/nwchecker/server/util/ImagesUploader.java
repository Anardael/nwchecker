package com.nwchecker.server.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.nwchecker.server.dao.ImageDAO;
import com.nwchecker.server.model.Image;

public class ImagesUploader {

	public static int uploadImage(String imageUrl) {
		Image image = new Image();
		image.setImage(getImage(imageUrl));
		return ImageDAO.addImage(image);
	}
	
	private static byte[] getImage(String imageUrl) {
		byte[] image = null;
		try {
			image = readImageToByteArray(imageUrl);	
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return image;
	}
	
	private static byte[] readImageToByteArray(String imageUrl) throws IOException {
		String imageFormat = 
				imageUrl.substring(imageUrl.lastIndexOf('.') + 1, imageUrl.length());
		BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, imageFormat, outStream);
		outStream.close();
		return outStream.toByteArray();
	}
}
