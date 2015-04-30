package com.nwchecker.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nwchecker.server.service.TaskPassService;
import com.nwchecker.server.service.TaskService;

@Controller
public class TaskStatisticController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskPassService taskPassService;
	
	static private final int PAGE_SIZE = 1;

	@RequestMapping(value = "/TaskStatistic.do", method = RequestMethod.GET)
	public ModelAndView getTaskStatistic(
			@RequestParam(value = "id", defaultValue = "45") int taskId,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		ModelAndView modelView = new ModelAndView("statistic/TaskStatistic");
		Map<String, Object> result = taskPassService.getStatisticOfSuccessfulTaskPasses(taskId, PAGE_SIZE, pageNumber);
		modelView.addAllObjects(result);
		modelView.addObject("currentPage", pageNumber);
		modelView.addObject("taskId", taskId);
		return modelView;
	}
/*	public ModelAndView getTaskStatistic(
			@RequestParam(value = "id", defaultValue = "45") int id,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		ModelAndView modelView = new ModelAndView("statistic/TaskStatistic");
		Map<String, Object> result = taskPassService.getPagedTaskPassesForContest(id, PAGE_SIZE, pageNumber);
		modelView.addAllObjects(result);
		modelView.addObject("currentPage", pageNumber);
		return modelView;
	}*/
}
