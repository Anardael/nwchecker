package com.nwchecker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.service.TaskService;

@Controller
public class TaskStatistic {
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="/TaskStatistic.do",
			params = {"id"},
			method=RequestMethod.GET)
	public ModelAndView getTaskStatistic(@RequestParam("id") int id){
		Task task = taskService.getTaskById(id);
		List<TaskPass> taskPassList = task.getTaskPassList();
		ModelAndView modelView = new ModelAndView("statistic/TaskStatistic");
		modelView.addObject("taskPassList", taskPassList);
		return modelView;
	}
}
