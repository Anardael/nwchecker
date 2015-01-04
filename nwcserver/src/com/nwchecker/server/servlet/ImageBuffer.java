package com.nwchecker.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nwchecker.server.dao.ImageDAO;

/**
 * Servlet implementation class ImageBuffer
 */
@WebServlet("/ImageBuffer")
public class ImageBuffer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String IMAGE_ID = "ImageID";
	private static final String SOURCE = "source";
	private static final String SRC_DB = "database";
	private static final String SRC_SESSION = "session";
	private static final String IMAGES_ATR = "images";
	
    public ImageBuffer() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String imageId = request.getParameter(IMAGE_ID);
    	String source = request.getParameter(SOURCE);
    	byte[] image = null;
    	if (source.equals(SRC_DB)) {
    		image = ImageDAO.getImageById(new Integer(imageId)).getImage();
		} else {
			HttpSession session = request.getSession();
			List<?> images = (List<?>) session.getAttribute(IMAGES_ATR);
			image = (byte[]) images.get(new Integer(imageId));
		}
    	response.getOutputStream().write(image);
	}
}
