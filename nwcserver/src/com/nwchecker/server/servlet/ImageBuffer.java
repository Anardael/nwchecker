package com.nwchecker.server.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nwchecker.server.model.Image;
import com.nwchecker.server.dao.ImageDAO;

/**
 * Servlet implementation class ImageBuffer
 */
@WebServlet("/ImageBuffer")
public class ImageBuffer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static HashMap<String, Image> imagesSet;
	
    public ImageBuffer() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	imagesSet = new HashMap<String, Image>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("ImageID");
		Image image = imagesSet.get(imageId);
		if (image == null) {
			image = ImageDAO.getImageById(new Integer(imageId));
			imagesSet.put(imageId, image);
		}
		response.getOutputStream().write(image.getImage());
	}
}
