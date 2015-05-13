package com.nwchecker.server.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nwchecker.server.service.TaskPassService;
import com.nwchecker.server.service.TaskService;

import java.util.TreeMap;

import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.json.TaskPassTableResponseList;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;

@Controller
public class TaskStatisticController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskPassService taskPassService;

	@RequestMapping(value = "/TaskStatistic.do", method = RequestMethod.GET)
	public ModelAndView getTaskStatistic(@RequestParam(value = "id") int taskId) {

		ModelAndView modelView = new ModelAndView("nwcserver.tasks.statistic");

		Task currentTask = taskService.getTaskById(taskId);
		Contest currentContest = currentTask.getContest();
		modelView.addObject("pageName", "contest");
		modelView.addObject("currentTask", currentTask);
		Map<Integer, String> taskTitles = new TreeMap<>();
		for (Task task : currentContest.getTasks()) {
			taskTitles.put(task.getId(), task.getTitle());
		}
		modelView.addObject("taskTitles", taskTitles);
		return modelView;
	}

	@RequestMapping(value = "/TaskStatisticTable.do", method = RequestMethod.GET)
	public @ResponseBody TaskPassTableResponseList getTaskPasses(
			@RequestParam int taskId, @RequestParam int jtStartIndex,
			@RequestParam int jtPageSize,
			@RequestParam(required = false) String jtSorting) {
		Long recordCount = taskPassService.getTaskPassSampleSize(taskId);
		List<TaskPassJson> records;
		if (jtSorting == null) {
			records = taskPassService.getPagedTaskPassesForTask(taskId,
					jtStartIndex, jtPageSize);
		} else {
			records = taskPassService.getPagedTaskPassesForTask(taskId,
					jtStartIndex, jtPageSize, jtSorting);
		}
		TaskPassTableResponseList jstr = new TaskPassTableResponseList("OK",
				records, recordCount);
		return jstr;
	}
}
