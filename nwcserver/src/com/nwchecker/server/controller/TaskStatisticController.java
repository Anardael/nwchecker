package com.nwchecker.server.controller;

import java.util.HashMap;
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

import java.util.TreeMap;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;

@Controller
public class TaskStatisticController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskPassService taskPassService;

	static private final int PAGE_SIZE = 10;

	@RequestMapping(value = "/TaskStatistic.do", method = RequestMethod.GET)
	public ModelAndView getTaskStatistic(
			@RequestParam(value = "id", defaultValue = "45") int taskId,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,

			@RequestParam(value = "username", required = false, defaultValue="true") boolean username,
			@RequestParam(value = "usernameType", required = false, defaultValue="Asc") String usernameType,

			@RequestParam(value = "compiler", required = false) boolean compiler,
			@RequestParam(value = "compilerType", required = false) String compilerType,

			@RequestParam(value = "execTime", required = false, defaultValue="true") boolean execTime,
			@RequestParam(value = "execTimeType", required = false, defaultValue="Asc") String execTimeType,

			@RequestParam(value = "memoryUsed", required = false) boolean memoryUsed,
			@RequestParam(value = "memoryUsedType", required = false) String memoryUsedType,

			@RequestParam(value = "passed", required = false) boolean passed,
			@RequestParam(value = "passedType", required = false) String passedType,

			@RequestParam(value = "attempts", required = false) boolean attempts,
			@RequestParam(value = "attemptsType", required = false) String attemptsType) {
		ModelAndView modelView = new ModelAndView("statistic/TaskStatistic");
		
		Map<String, String> orderParams = new HashMap<String, String>();
		if (username){
			orderParams.put("t.user.displayName", usernameType);
		}
		if (compiler){
			orderParams.put("compiler", compilerType);
		}
		if(execTime){
			orderParams.put("executionTime", execTimeType);
		}
		if(memoryUsed){
			orderParams.put("memoryUsed", memoryUsedType);
		}
		if(passed){
			orderParams.put("passed", passedType);
		}
		
		// Map<String, Object> result =
		// taskPassService.getStatisticOfSuccessfulTaskPasses(taskId, PAGE_SIZE,
		// pageNumber);
		Map<String, Object> result = taskPassService.getPagedTaskPassesForTask(
				taskId, pageSize, pageNumber, orderParams);
		modelView.addAllObjects(result);

		Task currentTask = taskService.getTaskById(taskId);
		Contest currentContest = currentTask.getContest();

		modelView.addObject("currentPage", pageNumber);
		modelView.addObject("taskId", taskId);

		Map<Integer, String> taskTitles = new TreeMap<>();
		for (Task task : currentContest.getTasks()) {
			taskTitles.put(task.getId(), task.getTitle());
		}
		modelView.addObject("taskTitles", taskTitles);
		return modelView;
	}
	/*
	 * public ModelAndView getTaskStatistic(
	 * 
	 * @RequestParam(value = "id", defaultValue = "45") int id,
	 * 
	 * @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
	 * ModelAndView modelView = new ModelAndView("statistic/TaskStatistic");
	 * Map<String, Object> result =
	 * taskPassService.getPagedTaskPassesForContest(id, PAGE_SIZE, pageNumber);
	 * modelView.addAllObjects(result); modelView.addObject("currentPage",
	 * pageNumber); return modelView; }
	 */
}
