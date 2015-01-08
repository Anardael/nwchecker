/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.exceptions.TaskException;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/getTasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
    public String getTaskById(@RequestParam("id") String id, HttpSession session, Model model) {
        int value = Integer.parseInt(id);
        Task task = taskService.getTaskById(value);
        model.addAttribute("task", task);
        return "taskLocal";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.GET)
    public String taskCreating(Model model) {
        model.addAttribute("taskAddForm", new Task());
        return "taskCreate";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(@Valid @ModelAttribute("taskAddForm") Task taskAddForm,
            BindingResult bindingResult,
            @RequestParam(value = "theoryLinks") String[] theory) {
        //check if there are errors in Task input fields:
        if (bindingResult.hasErrors()) {
            for (ObjectError e : bindingResult.getAllErrors()) {
                System.out.println(e);
            }
            return "taskCreate";
        }
        taskService.addTask(taskAddForm);
        List<TaskException> resultExceptions = new LinkedList<TaskException>();
        //nafig validation
        return "result";
    }
}
