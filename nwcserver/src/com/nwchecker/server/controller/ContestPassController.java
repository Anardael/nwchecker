package com.nwchecker.server.controller;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
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
import java.util.LinkedHashMap;
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

    @RequestMapping(value = "/contestSignUp")
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
            return "success";
        } else {
            return "wrongContest";
        }
    }
    
    @RequestMapping(value = "/passContest")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getStartedContest(Principal principal, Model model,
    								@RequestParam("contestId") int contestId,
    								@RequestParam("selectedTask") int selectedTask) {
    	User user = userService.getUserByUsername(principal.getName());
        for (Contest c : user.getContest()) {
            if (c.getId() == contestId) {
                Contest contest = contestService.getContestByID(contestId);
                model.addAttribute("contest", contest);
                model.addAttribute("selectedTask", selectedTask);
            	return "contests/contestPass";
            }
        }
        return "redirect:/index.do";
    }

    @RequestMapping(value = "/submitTask", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public
    @ResponseBody // Why MAP<,>? Maybe String?
    Map<String, Object> submitTask(Principal principal, @RequestParam(value = "taskId") int taskId,
                                   @RequestParam(value = "compilerId") int compilerId,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
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
