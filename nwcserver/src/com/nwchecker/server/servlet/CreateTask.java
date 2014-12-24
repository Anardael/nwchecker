package com.nwchecker.server.servlet;

import java.io.IOException;
import java.util.List;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Task task = new Task();
		task.setDescription(request.getParameter("taskDescription"));
		TaskDAO.addTask(task);
		
		List<Task> tasks = TaskDAO.getAllTasks();
		for (Task currentTask : tasks) {
			response.getWriter().println(currentTask.getDescription());
		}
	}
}
