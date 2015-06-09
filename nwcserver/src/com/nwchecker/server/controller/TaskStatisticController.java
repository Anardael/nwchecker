package com.nwchecker.server.controller;

import java.security.Principal;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.service.TaskPassService;
import com.nwchecker.server.service.TaskService;

import java.util.TreeMap;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.JTableResponseList;
import com.nwchecker.server.json.JsonViews;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;

@Controller
public class TaskStatisticController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskPassService taskPassService;
	private static final Logger LOG = Logger
			.getLogger(TaskStatisticController.class);

	@JsonView(JsonViews.TaskPassView.class)
	@RequestMapping(value = "/TaskStatisticTable", method = RequestMethod.GET)
	public @ResponseBody JTableResponseList getTaskPasses(
			@RequestParam int taskId,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, value = "sort") String sortingColumn,
			@RequestParam(required = false, value = "order") String sortingOrder,
			@RequestParam(required = false, value = "search") String filter) {
		LOG.info("Attempting to get task result data for page " + startIndex
				/ pageSize + " for task " + taskId);		
		JTableResponseList jTableResponse = new JTableResponseList(
				taskPassService.getPagedTaskPassJson(taskId, startIndex,
						pageSize, sortingColumn, sortingOrder, filter),
				taskPassService.getTaskPassEntryCount(taskId, filter));
		LOG.info("Successfully retuned task result data for page "
				+ startIndex / pageSize + 1 + " for task " + taskId);
		return jTableResponse;
	}
}
