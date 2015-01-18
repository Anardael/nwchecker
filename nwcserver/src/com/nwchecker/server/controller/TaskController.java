/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.validators.TaskValidator;
import com.nwchecker.server.json.ValidationResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/getTasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task";
    }

//------------------------------------------TEST
    @RequestMapping(value = "/newTaskJson.do", method = RequestMethod.POST)
    public @ResponseBody
    ValidationResponse processTaskJson(@ModelAttribute(value = "task") Task task,
            @RequestParam("contestId") int contestId, BindingResult result) {
        //Json response object:
        ValidationResponse res = new ValidationResponse();
        //validation in new TaskValdiator:
        new TaskValidator().validate(task, result);
        //if there are errors:
        if (result.hasErrors()) {
            //set json status FAIL:
            res.setStatus("FAIL");
            List<FieldError> allErrors = result.getFieldErrors();
            List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
            for (FieldError objectError : allErrors) {
                errorMesages.add(new ErrorMessage(objectError.getField(), messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
            }
            //set all errrors:
            res.setErrorMessageList(errorMesages);
        } else {
            //if validation passed:
            res.setStatus("SUCCESS");
            //get Contest for this Task:
            Contest c = contestService.getContestByID(contestId);
            //if Task is new:
            if (task.getId() == 0) {
                c.getTasks().add(task);
                task.setContest(c);
                //set contest for task(set foreign key for Contest):
                taskService.addTask(task);
            } else {
                //previous version of Task stored in Contest, so delete it:
                for (int i = 0; i < c.getTasks().size(); i++) {
                    if (c.getTasks().get(i).getId() == task.getId()) {
                        c.getTasks().remove(i);
                    }
                }
                //set new version of Task:
                c.getTasks().add(task);
                task.setContest(c);
                //contestService.updateContest(c);
                taskService.updateTask(task);
            }
            res.setResult(String.valueOf(task.getId()));
        }
        return res;
    }

    @RequestMapping(value = "/deleteTaskJson.do", method = RequestMethod.GET)
    public @ResponseBody
    ValidationResponse processDeleteTaskJson(@RequestParam("taskId") int taskId) {
        ValidationResponse result = new ValidationResponse();
        int contestId = taskService.getTaskById(taskId).getContest().getId();

        Contest c = contestService.getContestByID(contestId);
        for (int i = 0; i < c.getTasks().size(); i++) {
            if (c.getTasks().get(i).getId() == taskId) {
                c.getTasks().remove(i);
            }
        }
        contestService.updateContest(c);
        result.setStatus("SUCCESS");
        return result;
    }

}
