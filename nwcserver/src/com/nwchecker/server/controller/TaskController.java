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
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

    private static final Logger LOG
            = Logger.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private MessageSource messageSource;

    //Creating Task for Contest(contestId)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/newTaskJson.do", method = RequestMethod.POST)
    public @ResponseBody
    ValidationResponse processTaskJson(@ModelAttribute(value = "task") Task task,
            @RequestParam("contestId") int contestId, BindingResult result, Principal principal) {
        //check if user have acces to edit current contest:
        LOG.info("\"" + principal.getName() + "\"" + " tries to " + (task.getId() == 0 ? "create new" : ("edit exising (taskId=" + task.getId() + ")")) + " task.");
        boolean haveAccess = contestService.checkIfUserHaveAccessToContest(principal.getName(), contestId);

        if (haveAccess == true) {
            LOG.info("\"" + principal.getName() + "\"" + " have access to contest editing (id=" + contestId + ").");
        } else {
            LOG.info("\"" + principal.getName() + "\"" + " haven't access to contest editing (id=" + contestId + ").");
            return null;
        }
        //Json response object:
        ValidationResponse res = new ValidationResponse();
        //validation in new TaskValdiator:
        new TaskValidator().validate(task, result);
        //if there are errors:
        if (result.hasErrors()) {
            LOG.info("Task validation failed.");
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
            LOG.info("Task validation passed.");
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
                taskService.updateTask(task);
            }
            LOG.info("Task have been successfully saved.");
            res.setResult(String.valueOf(task.getId()));
        }
        return res;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/deleteTaskJson.do", method = RequestMethod.GET)
    public @ResponseBody
    ValidationResponse processDeleteTaskJson(@RequestParam("taskId") int taskId, Principal principal) {
        LOG.info("\"" + principal.getName() + "\"" + " tries to delete task(taskId=" + taskId + ")");
        ValidationResponse result = new ValidationResponse();
        int contestId = taskService.getTaskById(taskId).getContest().getId();
        //have current user acces to contest???
        boolean haveAccess = contestService.checkIfUserHaveAccessToContest(principal.getName(), contestId);
        if (haveAccess == true) {
            LOG.info("\"" + principal.getName() + "\"" + " have access to contest editing (id=" + contestId + ").");
        } else {
            LOG.info("\"" + principal.getName() + "\"" + " haven't access to contest editing (id=" + contestId + ").");
            return null;
        }
        Contest c = contestService.getContestByID(contestId);
        for (int i = 0; i < c.getTasks().size(); i++) {
            if (c.getTasks().get(i).getId() == taskId) {
                c.getTasks().remove(i);
            }
        }
        contestService.updateContest(c);
        LOG.info("Task (id=" + taskId + ") have been successfylly deleted.");
        result.setStatus("SUCCESS");
        return result;
    }

    //get taskModalForm by Json request:
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/newTaskForm.do", method = RequestMethod.GET)
    public String newTaskFormJson(Model model, @RequestParam("taskId") int taskId
    ) {
        Contest c = new Contest();
        List<Task> tasks = new LinkedList<Task>();
        Task t = new Task();
        t.setInputFileName("in");
        t.setOutputFileName("out");
        tasks.add(t);
        c.setTasks(tasks);
        model.addAttribute("taskIndex", taskId);
        model.addAttribute("contest", c);
        return "fragments/createNewTaskForm";
    }

}
