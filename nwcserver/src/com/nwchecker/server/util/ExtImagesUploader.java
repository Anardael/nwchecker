package com.nwchecker.server.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.nwchecker.server.dao.ImageDAO;
import com.nwchecker.server.model.Image;

public class ExtImagesUploader {

	public static int uploadExternalImage(String imageUrl) {
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
		InputStream inputStream = new FileInputStream(imageUrl);
		byte[] image = new byte[inputStream.available()];
    	inputStream.read(image);
    	inputStream.close();
    	return image;
		
//		String imageFormat = 
//				imageUrl.substring(imageUrl.lastIndexOf('.') + 1, imageUrl.length());
//		BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		ImageIO.write(bufferedImage, imageFormat, outStream);
//		outStream.close();
//		return outStream.toByteArray();
	}
}
