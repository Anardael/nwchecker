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
import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.model.Image;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.util.ImageTagUrlParser;
import com.nwchecker.server.util.ExtImagesUploader;

/**
 * Servlet implementation class CreateTask
 */
@WebServlet("/CreateTask")
public class CreateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HttpSession curSession = null;
	
    public CreateTask() {
        super();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		curSession = request.getSession();
    	Task task = new Task();
    	
		String taskDesc =request.getParameter("taskDescription");
		String imagesDomain = getImagesDomain(request);
		taskDesc = saveTaskDescriptionImages(taskDesc, imagesDomain);
		task.setDescription(taskDesc);
		int taskId = TaskDAO.addTask(task);

		String redirectAddress = request.getServletContext().getContextPath()
								+"/ui/taskView?TaskID=" + taskId;
		response.sendRedirect(redirectAddress);
	}
	
	private String getImagesDomain(HttpServletRequest request) {
		return request.getScheme() + "://" 
			 + request.getServerName() + ":"
			 + request.getServerPort() 
			 + request.getServletContext().getContextPath()
			 + "/ImageBuffer";
	}
	
	private String saveTaskDescriptionImages(String taskDesc, String imagesDomain) {
		ImageTagUrlParser parser = new ImageTagUrlParser();
		parser.setString(taskDesc);
		parser = parseAndSaveImages(parser, imagesDomain);
		taskDesc = parser.getString();
		return taskDesc;
	}
	
	private ImageTagUrlParser parseAndSaveImages(ImageTagUrlParser parser, String imagesDomain) {
		while (!parser.isEnd()) {
			String imageUrl = parser.nextUrl();
			if (imageUrl != "") {
				Integer imageId = saveImage(imageUrl);
				parser.replaceUrl(imagesDomain + "?source=database&ImageID=" + imageId);
			}
		}
		return parser;
	}
	
	private int saveImage(String imageUrl) {
		if (imageUrl.lastIndexOf("source=session") != -1) {
			String imageIndex = imageUrl.substring(imageUrl.lastIndexOf('=') + 1,
												   imageUrl.length());
			return saveImageFromSession(new Integer(imageIndex));
		} else {
			return ExtImagesUploader.uploadExternalImage(imageUrl);
		}
	}
	
	private int saveImageFromSession(int index) {
		List<?> sessionImg = (List<?>) curSession.getAttribute("images");
		if (sessionImg == null) {
			return 0;
		}
		Image image = new Image();
		image.setImage((byte[]) sessionImg.get(index));
		return ImageDAO.addImage(image);
	}
}
