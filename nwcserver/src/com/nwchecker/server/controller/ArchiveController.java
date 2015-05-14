package com.nwchecker.server.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchiveController {

	@Autowired
	TaskService taskService;
	private static final Logger LOG = Logger.getLogger(ArchiveController.class);
	@RequestMapping("/etiam")
	public String archivePage(Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		LOG.debug("Someone accessed archive page");
		List<Task> tasks = taskService.getPagedTasksByContestStatus(
				Contest.Status.ARCHIVE, page, pageSize);
		Long pageCount = taskService.getPageCount(Contest.Status.ARCHIVE,
				pageSize);
		List<TaskJson> tj = new ArrayList<TaskJson>();
		for (Task task : tasks) {
			tj.add(TaskJson.createTaskJson(task));
		}
		
		model.addAttribute("tasks", tj);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageName", "archive");
		LOG.debug("Successfully data for archive page");
		return "nwcserver.static.archive";
	}
}