package com.nwchecker.server.controller;

import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.utils.ContestStartTimeComparator;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private ContestService contestService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ContestPassService contestPassService;
    @Autowired
    private CompilerDAO compilerService;

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
        Task currentTask = taskService.getTaskById(taskId);
        model.addAttribute("currentTask", currentTask);
        User user = userService.getUserByUsername(principal.getName());
        //check if contest status provide passing:
        if (!(currentTask.getContest().getStatus() == Contest.Status.GOING ||
                currentTask.getContest().getStatus() == Contest.Status.ARCHIVE)) {
            return "access/accessDenied403";
        }
        //if contest is going:
        ContestPass currentContestPass = null;
        if (currentTask.getContest().getStatus() == Contest.Status.GOING) {
            //check if user has contestPass for this contest:
            boolean contains = false;
            for (ContestPass c : user.getContestPassList()) {
                if (c.getContest().equals(currentTask.getContest())) {
                    contains = true;
                    currentContestPass = c;
                    break;
                }
            }
            if (!contains) {
                ContestPass contestPass = new ContestPass();
                contestPass.setContest(currentTask.getContest());
                contestPass.setUser(user);
                contestPassService.saveContestPass(contestPass);
            }
        }
        model.addAttribute("isArchive", (currentTask.getContest().getStatus() == Contest.Status.ARCHIVE));
        //get contest tasks titles
        Map<Integer, String> taskTitles = new TreeMap<>();
        Contest currentContest = currentTask.getContest();
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

        return "contests/contestPass";
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

    /**
     * This mapped method used to return page when user
     * can view completed contests.
     * <p>
     *
     * @param model Spring Framework model for this page
     * @return <b>rating.jsp</b> Returns page with completed contests list
     */
    @RequestMapping(value = "/rating", method = RequestMethod.GET)
    public String getRating(Model model) {
        List<Contest> archivedContests = contestService.getContestByStatus(Contest.Status.ARCHIVE);
        Collections.sort(archivedContests, new ContestStartTimeComparator());
        Collections.reverse(archivedContests);
        model.addAttribute("archivedContests", archivedContests);
        return "contests/rating";
    }

    /**
     * This mapped method used to show page that contains
     * contest results of contest participants.
     * <p>
     *
     * @param model Spring Framework model for this page
     * @param id ID of contest
     * @return <b>contestResults.jsp</b> Returns page with contest statistic
     */
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String getResults(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("contestId", id);
        Contest contest = contestService.getContestByID(id);
        model.addAttribute("contestTitle", contest.getTitle());
        SimpleDateFormat formatStart = new SimpleDateFormat();
        model.addAttribute("contestStart", formatStart.format(contest.getStarts()));
        Calendar contestDuration = Calendar.getInstance();
        contestDuration.setTime(contest.getDuration());
        model.addAttribute("contestDurationHours", contestDuration.get(Calendar.HOUR));
        model.addAttribute("contestDurationMinutes", contestDuration.get(Calendar.MINUTE));
        return "contests/contestResults";
    }

    /**
     * This mapped method used to return results of contest
     * participants in JSON format.
     * <p>
     *
     * @param id ID of contest
     * @return <b>JSON</b> Returns <b>results of contest participants</b>
     */
    @RequestMapping(value = "/resultsList", method = RequestMethod.GET)
    public @ResponseBody List<ContestPassJson> getResultsList(@RequestParam(value = "id") int id) {
        List<ContestPass> contestPasses = contestPassService.getContestPasses(id);
        Collections.sort(contestPasses);
        List<ContestPassJson> jsonData = new ArrayList<>();
        for (ContestPass contestPass : contestPasses) {
            jsonData.add(ContestPassJson.createContestPassJson(contestPass));
        }
        return jsonData;
    }
}
