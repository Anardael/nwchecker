package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.utils.PaginationWrapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchiveController {

	@Autowired
	TaskService taskService;
	private static final Logger LOG = Logger.getLogger(ArchiveController.class);
	
	@Link(label="Archive", family="archive", parent = "")
	@RequestMapping("/etiam")
	public String archivePage(Model model,
			@RequestParam(defaultValue = "1", value="page") int pageNumber,
			@RequestParam(defaultValue = "5") int pageSize,
			@ModelAttribute("filterText") String filter) {
		System.out.println(filter);
		LOG.debug("Someone accessed archive page");
		PaginationWrapper<TaskJson> paginatedTaskJson = taskService
				.getTaskJsonForPagination(Contest.Status.ARCHIVE, pageSize,
						pageNumber, filter);
		model.addAttribute("tasks", paginatedTaskJson.getDataList());
		model.addAttribute("pageCount", paginatedTaskJson.getPageCount());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageName", "archive");
		LOG.debug("Successfully data for archive page");
		return "nwcserver.static.archive";
	}
}