/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.aspect.CheckTeacherAccess;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.validators.TaskValidator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    @Autowired
    private TaskValidator taskValidator;

    //Creating Task for Contest(contestId)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @CheckTeacherAccess
    @RequestMapping(value = "/newTaskJson.do", method = RequestMethod.POST)
    public @ResponseBody
    ValidationResponse newTaskJson(@RequestParam("contestId") int contestId, Principal principal,
            MultipartHttpServletRequest request, @ModelAttribute(value = "task") Task task,
            BindingResult result) {
        //Json response object:
        ValidationResponse res = new ValidationResponse();
        //validation in new TaskValdiator:
        taskValidator.validate(task, result);
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
            //I/O files:
            Iterator<String> itr = request.getFileNames();
            LinkedList<TaskData> data = new LinkedList<TaskData>();
            while (itr.hasNext()) {
                try {
                    MultipartFile mpf = request.getFile(itr.next());
                    TaskData newd = new TaskData();
                    newd.setInputData(mpf.getBytes());
                    mpf = request.getFile(itr.next());
                    newd.setOutputData(mpf.getBytes());
                    newd.setTask(task);
                    data.add(newd);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //set i/o data to task:
            task.setInOutData(data);
            //get Contest for this Task:
            Contest c = contestService.getContestByID(contestId);
            //if Task is new:
            if (task.getId() == 0) {
                c.getTasks().add(task);
                task.setContest(c);
                //set contest for task(set foreign key for Contest):
                taskService.addTask(task);
            } else {
                //get task data files avaible:
                List<TaskData> avaibleData = taskService.getTaskById(task.getId()).getInOutData();
                for (TaskData dataTest : avaibleData) {
                    task.getInOutData().add(dataTest);
                }
                task.getInOutData().addAll(avaibleData);
                task.setContest(c);
                taskService.updateTask(task);
            }
            LOG.info("Task have been successfully saved.");
            res.setResult(String.valueOf(task.getId()));
        }
        return res;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @CheckTeacherAccess
    @RequestMapping(value = "/deleteTaskJson.do", method = RequestMethod.GET)
    public @ResponseBody
    ValidationResponse processDeleteTaskJson(@RequestParam("contestId") int contestId, Principal principal, @RequestParam("taskId") int taskId) {
        ValidationResponse result = new ValidationResponse();
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

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @CheckTeacherAccess
    @RequestMapping(value = "/getTaskTestData", method = RequestMethod.GET)
    public void getFile(@RequestParam("contestId") int contestId, Principal principal,
            @RequestParam("testId") int testId, @RequestParam("type") String type,
            HttpServletResponse response) throws IOException {
        //first of all: find test file:
        TaskData data = taskService.getTaskData(testId);
        ByteArrayInputStream stream = null;
        if (type != null && type.equals("in")) {
            stream = new ByteArrayInputStream(data.getInputData());
        }
        if (type != null && type.equals("out")) {
            stream = new ByteArrayInputStream(data.getOutputData());
        }

        response.setContentType("application/txt");
        response.setHeader("Content-Disposition", "attachment; filename=" + (type.equals("in") ? "in" : "out") + "_id" + testId + ".txt");
        org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @CheckTeacherAccess
    @RequestMapping(value = "/getAvaibleTests", method = RequestMethod.GET)
    public String getTestFiles(@RequestParam("contestId") int contestId, Principal principal,
            @RequestParam("taskId") int taskId, @RequestParam("localTaskId") int localTaskId, Model model) {
        Task t = taskService.getTaskById(taskId);
        model.addAttribute("taskData", t.getInOutData());
        model.addAttribute("taskId", localTaskId);
        model.addAttribute("contestId", contestId);
        return "fragments/taskDataView";

    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @CheckTeacherAccess
    @RequestMapping(value = "/deleteTaskTestFile", method = RequestMethod.GET)
    public @ResponseBody
    ValidationResponse deleteTestFile(@RequestParam("contestId") int contestId, Principal principal,
            @RequestParam("taskTestId") int testId) {
        ValidationResponse validationResponse = new ValidationResponse();
        //delete taskData:
        taskService.deleteTaskData(testId);
        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }
}
