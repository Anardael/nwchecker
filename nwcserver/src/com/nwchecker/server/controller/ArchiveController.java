package com.nwchecker.server.controller;


import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.utils.PaginationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchiveController {

	@Autowired
	TaskService taskService;

	@RequestMapping("/etiam")
	public String archivePage(Model model,
			@RequestParam(defaultValue = "1", value="page") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		System.out.println(pageNumber + " " + pageSize);
		PaginationWrapper<TaskJson> paginatedTaskJson = taskService
				.getTaskJsonForPagination(Contest.Status.ARCHIVE, pageSize,
						pageNumber);
		model.addAttribute("tasks", paginatedTaskJson.getDataList());
		model.addAttribute("pageCount", paginatedTaskJson.getPageCount());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageName", "archive");
		return "nwcserver.static.archive";
	}
}