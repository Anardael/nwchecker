package com.nwchecker.server.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImageUploader
 */
@WebServlet("/ImageUploader")
@MultipartConfig
public class ImageUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String INPUT_FILE = "file";
	private static final String IMAGES_ATR = "images";
	private static final String IMAGES_LENG_ATR = "imagesLength";
	private static final int SESSION_MAX_INACTIVE_INTERVAL = 30*60;
	
    public ImageUploader() {
        super();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	byte[] image = uploadUserImage(request);
    	HttpSession session = request.getSession();
    	List<byte[]> images = (List) session.getAttribute(IMAGES_ATR);
    	if (images == null) {
    		images = createImagesList(session);
    	}
    	images.add(image);
    	session.setAttribute(IMAGES_LENG_ATR, images.size());
    	String redirectAddress = request.getServletContext().getContextPath()
							   + "/ui/fileUpload";
    	response.sendRedirect(redirectAddress);
	}
    
    private byte[] uploadUserImage(HttpServletRequest request) throws IOException, ServletException {
    	InputStream inputStream = request.getPart(INPUT_FILE).getInputStream();
    	byte[] image = new byte[inputStream.available()];
    	inputStream.read(image);
    	inputStream.close();
    	return image;
    }
    
    private List<byte[]> createImagesList(HttpSession session) {
    	session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
    	ArrayList<byte[]> images = new ArrayList<>();
		session.setAttribute(IMAGES_ATR, images);
		return images;
    }
}
