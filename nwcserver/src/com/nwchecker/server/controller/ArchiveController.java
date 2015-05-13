package com.nwchecker.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArchiveController {

	@Autowired
	TaskService taskService;

	@RequestMapping("/etiam")
	public String archivePage(Model model,
			@RequestParam(defaultValue = "1") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		System.out.println(pageNumber + " " + pageSize);
		List<Task> tasks = taskService
				.getPagedTasksByContestStatus(Contest.Status.ARCHIVE, pageNumber, pageSize);
		List<TaskJson> tj = new ArrayList<TaskJson>();
		for (Task task : tasks) {
			tj.add(TaskJson.createTaskJson(task));
		}
		model.addAttribute("tasks", tj);
		return "sidePanelPages/archive";
	}

	@RequestMapping(value = "/archivedTasks", method = RequestMethod.GET)
	public @ResponseBody List<TaskJson> archivedTasks() {
		List<Task> tasks = taskService
				.getTasksByContestStatus(Contest.Status.ARCHIVE);
		List<TaskJson> tj = new ArrayList<TaskJson>();
		for (Task task : tasks) {
			tj.add(TaskJson.createTaskJson(task));
		}
		return tj;
	}
}