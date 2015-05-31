package com.nwchecker.server.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.JTableResponseList;
import com.nwchecker.server.json.JsonViews;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArchiveController {

	@Autowired
	TaskService taskService;
	private static final Logger LOG = Logger.getLogger(ArchiveController.class);

	@Link(label = "archive.caption", family = "archive", parent = "")
	@RequestMapping("/etiam")
	public String archivePage(Model model) {
		LOG.info("Someone accessed archive page");
		model.addAttribute("pageName", "archive");
		LOG.info("Successfully data for archive page");
		return "nwcserver.static.archive";
	}

	@JsonView(JsonViews.ForArchive.class)
	@RequestMapping("/archiveTable")
	public @ResponseBody JTableResponseList archivedTableResponse(
			@RequestParam("offset") int startIndex,
			@RequestParam("limit") int pageSize,
			@RequestParam(required = false, value = "sort") String sortingColumn,
			@RequestParam(required = false, value = "order") String sortingOrder,
			@RequestParam(required = false, value = "search") String filter) {
		LOG.info("Successfully data for archive page");
		JTableResponseList jTableResponse = new JTableResponseList(
				taskService.getPagedTasksByContestStatus(
						Contest.Status.ARCHIVE, startIndex, pageSize,
						sortingColumn, sortingOrder, filter),
				taskService.getRecordCount(Contest.Status.ARCHIVE, filter));
		LOG.info("Successfully data for archive page");
		return jTableResponse;
	}

	@JsonView(JsonViews.SingleTask.class)
	@RequestMapping("/getTaskDetails")
	public @ResponseBody Task getTaskDetails(@RequestParam("taskId") int taskId) {
		LOG.info("Attempted to get task details");
		return taskService.getTaskById(taskId);
	}
}