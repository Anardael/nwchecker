package com.nwchecker.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nwchecker.server.service.TaskPassService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.utils.OrderParams;

import java.util.TreeMap;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;

@Controller
public class TaskStatisticController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskPassService taskPassService;

	@RequestMapping(value = "/TaskStatistic.do", method = RequestMethod.GET)
	public ModelAndView getTaskStatistic(
			@RequestParam(value = "id") int taskId,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,

			@ModelAttribute(value = "orderParams") OrderParams orderParams) {
		
		ModelAndView modelView = new ModelAndView("nwcserver.tasks.abstract");

		Map<String, String> orderParamsMap = taskPassService
				.parseOrderParams(orderParams);

		Map<String, Object> result = taskPassService.getPagedTaskPassesForTask(
				taskId, pageSize, pageNumber, orderParamsMap);
		modelView.addAllObjects(result);

		Task currentTask = taskService.getTaskById(taskId);
		Contest currentContest = currentTask.getContest();

		modelView.addObject("currentPage", pageNumber);
		modelView.addObject("currentTask", currentTask);

		Map<Integer, String> taskTitles = new TreeMap<>();
		for (Task task : currentContest.getTasks()) {
			taskTitles.put(task.getId(), task.getTitle());
		}
		modelView.addObject("taskTitles", taskTitles);
		return modelView;
	}

	@RequestMapping(value = "/TaskStatistic.do", method = RequestMethod.POST)
	public ModelAndView changeOrdering(
			@RequestParam(value = "id", defaultValue = "45") int taskId,

			@ModelAttribute(value = "orderParams") OrderParams orderParams) {
		ModelAndView modelView = new ModelAndView("nwcserver.tasks.abstract");
		Map<String, String> orderParamsMap = taskPassService
				.parseOrderParams(orderParams);

		Map<String, Object> result = taskPassService.getPagedTaskPassesForTask(
				taskId, orderParams.getPageSize(), 1, orderParamsMap);
		modelView.addAllObjects(result);

		Task currentTask = taskService.getTaskById(taskId);
		Contest currentContest = currentTask.getContest();

		modelView.addObject("currentPage", 1);
		modelView.addObject("taskId", taskId);

		Map<Integer, String> taskTitles = new TreeMap<>();
		for (Task task : currentContest.getTasks()) {
			taskTitles.put(task.getId(), task.getTitle());
		}
		modelView.addObject("taskTitles", taskTitles);
		return modelView;
	}

}
