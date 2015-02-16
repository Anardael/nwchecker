package com.nwchecker.server.controller;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskPassService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 11.02.2015.
 */
@Controller("contestPassController")
public class ContestPassController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskPassService taskPassService;

    @RequestMapping(value = "/contestSignUp.do")
    @PreAuthorize("hasRole('ROLE_USER')")
    public
    @ResponseBody
    String signUpForContest(Principal principal,
                            @RequestParam(value = "id") int contestId) {
        User user = userService.getUserByUsername(principal.getName());
        //user already has contest?
        for (Contest c : user.getContest()) {
            if (c.getId() == contestId) {
                return "hasContest";
            }
        }
        //if user hasn't contest:
        Contest requiredContest = contestService.getContestByID(contestId);
        if (requiredContest != null && (requiredContest.getStatus() == Contest.Status.RELEASE ||
                requiredContest.getStatus() == Contest.Status.GOING)) {
            user.getContest().add(requiredContest);
            userService.updateUser(user);
            return "success";
        } else {
            return "wrongContest";
        }
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/passTask.do", method = RequestMethod.GET)
    public String getTaskForPass(Principal pricnipal, @RequestParam("id") int taskId,
                                 Model model) {
        Task currentTask = taskService.getTaskById(taskId);
        User user = userService.getUserByUsername(pricnipal.getName());
        //if user has no access to task contest of if task contest status is not GOING:
        if ((!user.getContest().contains(currentTask.getContest())) || (currentTask.getContest().getStatus() != Contest.Status.GOING)) {
            return "access/accessDenied403";
        }
        model.addAttribute("currentTask", currentTask);
        //get list of contest tasks id
        List<Task> contestTasks = currentTask.getContest().getTasks();
        List<Integer> tasksIdList = new ArrayList<>();
        for (Task task : contestTasks) {
        	tasksIdList.add(task.getId());
        }
        model.addAttribute("tasksIdList", tasksIdList);
        //get list of passed/failed tasks, and forward it to UI:
        Map<Integer, Boolean> taskResults = new LinkedHashMap<>();
        for (TaskPass taskPass : user.getTaskPass()) {
            //if not contains:
            if (!taskResults.containsKey(taskPass.getId())) {
                taskResults.put(taskPass.getId(), taskPass.isPassed());
                continue;
            }
            //if contains and new result if success:
            if ((!taskResults.get(taskPass.getId())) && taskPass.isPassed()) {
                taskResults.put(taskPass.getId(), taskPass.isPassed());
            }
        }
        model.addAttribute("taskResults", taskResults);
        return "contests/contestPass";
    }

    @RequestMapping(value = "/submitTask", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public
    @ResponseBody
        // Why MAP<,>? Maybe String?
    Map<String, Object> submitTask(Principal principal, @RequestParam(value = "taskId") int taskId,
                                   @RequestParam(value = "compilerId") int compilerId,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new LinkedHashMap<>();
        Task task = taskService.getTaskById(taskId);
        User user = userService.getUserByUsername(principal.getName());
        //check access:
        if (!user.getContest().contains(task.getContest())) {
            result.put("accessDenied", true);
            return result;
        }
        result = taskPassService.checkTask(user, task, compilerId, file.getBytes());
        return result;
    }
}
