/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/getTasks")
    public String getTasks(HttpSession session, Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
    public String getTaskById(@RequestParam("id") String id, HttpSession session, Model model) {
        //get index param:
        int value = Integer.parseInt(id);
        Task task = taskService.getTaskById(value);
        model.addAttribute("task", task);
        return "taskLocal";
    }

    @RequestMapping("/taskCreating")
    public String taskCreating(HttpSession session, Model model) {
        System.out.println("");
        return "taskCreate";
    }

    @RequestMapping("/addTask")
    public String addTask(HttpSession session, HttpServletRequest request, Model model) {
        System.out.println("--- Model data ---");
        Map modelMap = model.asMap();
        for (Object modelKey : modelMap.keySet()) {
            Object modelValue = modelMap.get(modelKey);
            System.out.println(modelKey + " -- " + modelValue);
        }

        return "taskCreate";
    }
}
