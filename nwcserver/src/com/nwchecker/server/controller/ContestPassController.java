package com.nwchecker.server.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.nwchecker.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.model.*;

/**
 * <h1>Contest Pass Controller</h1>
 * This spring controller contains mapped methods, that allows users
 * to pass contests and view contests results.
 * <p>
 * <b>Note:</b>Only USERs allows to took part in contests.
 *
 * @author Roman Zayats
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-03
 */
@Controller("contestPassController")
public class ContestPassController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ContestPassService contestPassService;
    @Autowired
    private CompilerDAO compilerService;
    @Autowired
    private TaskPassService taskPassService;
    @Autowired
    private ContestService contestService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/passContest", method = RequestMethod.GET)
    public String getContestForPass(Principal principal, @RequestParam("id") int contestId,
                                    Model model) {
        Contest currentContest = contestService.getContestByID(contestId);

        if(!model.containsAttribute("currentTask")){
            //Sending task statistic
            Task firstTaskCurrentContest = currentContest.getTasks().get(0);
            Long successful = taskPassService.getSuccessfulTaskPassEntryCount(firstTaskCurrentContest.getId());
            Long all = taskPassService.getTaskPassEntryCount(firstTaskCurrentContest.getId());
            if(!(all == 0)){
                double rate = successful.doubleValue() / all.doubleValue();
                model.addAttribute("taskSuccessRate", rate);
                model.addAttribute("currentTask", firstTaskCurrentContest);
            } else{
                //double rate = successful.doubleValue() / all.doubleValue();
                model.addAttribute("taskSuccessRate", 0);
                model.addAttribute("currentTask", firstTaskCurrentContest);
            }
        }
        if (currentContest.getTypeContest() != null
                && currentContest.getTypeContest().isDynamic() != null
                && currentContest.getTypeContest().isDynamic()) {
            model.addAttribute("currentContestId", currentContest.getId());
        } else  {
            model.addAttribute("currentContestId", null);
        }
        User user = userService.getUserByUsername(principal.getName());
        //check if contest status provide passing:
        if (!(currentContest.getStatus() == Contest.Status.GOING ||
                currentContest.getStatus() == Contest.Status.ARCHIVE)) {
            model.addAttribute("pageName", "result");
            return "nwcserver.403";
        }
        //if contest is going:
        ContestPass currentContestPass = null;
        if (currentContest.getStatus() == Contest.Status.GOING) {
            //check if user has contestPass for this contest:
            boolean contains = false;
            for (ContestPass c : user.getContestPassList()) {
                if (c.getContest().equals(currentContest)) {
                    contains = true;
                    currentContestPass = c;
                    break;
                }
            }
            if (!contains) {
                ContestPass contestPass = new ContestPass();
                contestPass.setContest(currentContest);
                contestPass.setUser(user);
                contestPassService.saveContestPass(contestPass);
            }
        }
        model.addAttribute("isArchive", (currentContest.getStatus() == Contest.Status.ARCHIVE));
        //get contest tasks titles
        Map<Integer, String> taskTitles = new TreeMap<>();

        for (Task task : currentContest.getTasks()) {
            taskTitles.put(task.getId(), task.getTitle());
        }
        model.addAttribute("taskTitles", taskTitles);

        // get contest ending time in GMT
        Calendar endDate = Calendar.getInstance();
        Calendar duration = Calendar.getInstance();
        endDate.setTime(currentContest.getStarts());
        duration.setTime(currentContest.getDuration());
        endDate.add(Calendar.HOUR, duration.get(Calendar.HOUR));
        endDate.add(Calendar.MINUTE, duration.get(Calendar.MINUTE));
        endDate.add(Calendar.SECOND, duration.get(Calendar.SECOND));
        long gtmMillis = endDate.getTimeInMillis() - endDate.getTimeZone().getRawOffset();
        model.addAttribute("contestEndTimeGTM", gtmMillis);

        //get list of passed/failed tasks, and forward it to UI:
        if (currentContestPass != null) {
            Map<Integer, Boolean> taskResults = new LinkedHashMap<>();
            for (TaskPass taskPass : currentContestPass.getTaskPassList()) {
                //if not contains:
                if (!taskResults.containsKey(taskPass.getTask().getId())) {
                    taskResults.put(taskPass.getTask().getId(), taskPass.isPassed());
                    continue;
                }
                //if contains and new result if success:
                if ((!taskResults.get(taskPass.getTask().getId())) && taskPass.isPassed()) {
                    taskResults.put(taskPass.getTask().getId(), taskPass.isPassed());
                }
            }
            model.addAttribute("taskResults", taskResults);
        }

        model.addAttribute("compilers", compilerService.getAllCompilers());
        model.addAttribute("pageName", "task");
        return "nwcserver.tasks.pass";
    }

    /**
     * This mapped method used to return page that allows user to
     * pass contest.
     * <p>
     * <b>Note:</b>Only USER has rights to use this method.
     *
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param taskId Id of selected task
     * @param model Spring Framework model for this page
     * @return Page when user can continue passing contest if <b>success</b>.
     *         Page <b>accessDenied403.jsp</b> if <b>fails</b>.
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/passTask", method = RequestMethod.GET)
    public String getTaskForPass(Principal principal, @RequestParam("id") int taskId,
                                 Model model) {
    	//Sending task statistic
    	Long successful = taskPassService.getSuccessfulTaskPassEntryCount(taskId);
    	Long all = taskPassService.getTaskPassEntryCount(taskId);
    	if(!(all == 0)){
    		double rate = successful.doubleValue() / all.doubleValue();
    		model.addAttribute("taskSuccessRate", rate);
    	}
        Task currentTask = taskService.getTaskById(taskId);
        model.addAttribute("currentTask", currentTask);

        int currentContest = currentTask.getContest().getId();
        return getContestForPass(principal, currentContest, model);
    }

    /**
     * This mapped method used to receive user's answer for one task,
     * send this data to <b>checker</b>, receive answer and return it
     * to user.
     * <p>
     * <b>Note:</b>Only USER has rights to use this method.
     *
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param taskId ID of task that submits
     * @param compilerId ID of compiler that must be used to by checker
     * @param file File that contains user's source code that get solution
     *             of task problem
     * @return Result data
     * @throws IOException If problems occurs while file sending/receiving
     */
    @RequestMapping(value = "/submitTask", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public
    @ResponseBody
    Map<String, Object> submitTask(Principal principal, @RequestParam(value = "id") int taskId,
                                   @RequestParam(value = "compilerId") int compilerId,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new LinkedHashMap<>();
        Task task = taskService.getTaskById(taskId);
        User user = userService.getUserByUsername(principal.getName());
        //check access:
        ContestPass contestPass = null;
        if (task.getContest().getStatus() == Contest.Status.GOING) {
            //check if user contains contestPass:
            for (ContestPass c : user.getContestPassList()) {
                if (c.getContest().equals(task.getContest())) {
                    contestPass = c;
                }
            }
        }
        if (task.getContest().getStatus() == Contest.Status.GOING && contestPass != null) {
            result = contestPassService.checkTask(true, contestPass, task, compilerId, file.getBytes(), user);
        } else if (task.getContest().getStatus() == Contest.Status.ARCHIVE) {
            //archive:
            result = contestPassService.checkTask(false, contestPass, task, compilerId, file.getBytes(), user);
        } else {
            result.put("accessDenied", true);
        }
        return result;
    }
}
