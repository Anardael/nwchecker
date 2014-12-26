/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/task")
    public String getTasks(HttpSession session, Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @RequestMapping(value = "/getTask", method = RequestMethod.GET)
    public String getTask(@RequestParam("id") String id, HttpSession session, Model model) {
        //get index param:
        int value = Integer.parseInt(id);
        Task task = taskService.getTaskById(value);
        model.addAttribute("task", task);
        return "taskLocal";
    }

    @RequestMapping("/addTask")
    public String addTask(HttpSession session, Model model) {
        System.out.println("");
        return "taskCreate";
    }
}
