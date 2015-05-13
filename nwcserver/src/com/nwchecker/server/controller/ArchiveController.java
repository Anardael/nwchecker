package com.nwchecker.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.ArchiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArchiveController {
	
	@Autowired
	ArchiveService as;
	
    @RequestMapping("/etiam")
    public String archivePage(/*@RequestParam(value="page", required = false)int page,*/ Model model){
    	return "sidePanelPages/archive";
    }
    @RequestMapping(value = "/archivedTasks", method =RequestMethod.GET)
    public @ResponseBody List<TaskJson> archivedTasks(){
    	List<Task> tasks = as.getArchivedTasks();
    	List<TaskJson> tj = new ArrayList<TaskJson>();
    	for (Task task : tasks){
    		tj.add(TaskJson.createTaskJson(task));
    	} 
    	return tj;
    }
}