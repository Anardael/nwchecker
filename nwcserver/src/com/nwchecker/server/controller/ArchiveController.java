package com.nwchecker.server.controller;

import java.security.Principal;

import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.utils.PaginationWrapper;

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
			@RequestParam(defaultValue = "1", value="page") int pageNumber,
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
		System.out.println(pageNumber + " " + pageSize);
		PaginationWrapper<TaskJson> paginatedTaskJson = taskService
				.getTaskJsonForPagination(Contest.Status.ARCHIVE, pageSize,
						pageNumber);
		model.addAttribute("tasks", paginatedTaskJson.getDataList());
		model.addAttribute("pageCount", paginatedTaskJson.getPageCount());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageName", "archive");
		LOG.debug("Successfully data for archive page");
		return "nwcserver.static.archive";
	}
}