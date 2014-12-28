package com.nwchecker.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.model.Task;

/**
 * Servlet implementation class CreateTask
 */
@WebServlet("/CreateTask")
public class CreateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateTask() {
        super();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Task task = new Task();
		task.setDescription(request.getParameter("taskDescription"));
		String imagesDomain = getImagesDomain(request);
		int taskId = TaskDAO.addTask(task, imagesDomain);
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
}
