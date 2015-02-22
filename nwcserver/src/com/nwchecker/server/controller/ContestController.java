/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.exceptions.ContestAccessDenied;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.UserJson;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestEditWatcherService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.ScheduleServiceImpl;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.validators.ContestValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Роман
 */
@Controller("contestController")
public class ContestController {

    private static final Logger LOG
            = Logger.getLogger(ContestController.class);

    @Autowired
    private ContestService contestService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ContestValidator contestValidator;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private ContestEditWatcherService contestEditWatcherService;


    @RequestMapping("/getContests")
    public String getContests(Model model, Principal principal) {
        //get all avaible  сontests from DB:
        List<Contest> allContests = contestService.getContests();
        //get unhidden contests:
        List<Contest> unhidden = new LinkedList<Contest>();
        for (Contest c : allContests) {
            if (!c.isHidden()) {
                unhidden.add(c);
            }
        }
        if (principal == null) {
            //return all "unhidden" contests:
            model.addAttribute("contests", unhidden);
            return "contests/contest";
        }

        User user = userService.getUserByUsername(principal.getName());

        if (((UsernamePasswordAuthenticationToken) principal).getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
            List<String> editableContestIndexes = new LinkedList<String>();
            //getContests which user can edit:
            if ((user.getContest() != null) && (user.getContest().size() > 0)) {
                for (Contest c : user.getContest()) {
                    if (c.getStatus().equals(Contest.Status.PREPARING) || c.getStatus().equals(Contest.Status.ARCHIVE)) {
                        //set index for view
                        if (c.getStatus().equals(Contest.Status.PREPARING)) {
                            editableContestIndexes.add("index" + c.getId() + "index");
                        }
                        //add Contest to unhidden list:
                        if (c.isHidden()) {
                            unhidden.add(c);
                        }
                    }
                }
            }
            model.addAttribute("editContestIndexes", editableContestIndexes);
            //set usernames for editing contests:
            model.addAttribute("nowContestEdits", contestEditWatcherService.getNowEditsMap());
        }
        model.addAttribute("contests", unhidden);
        return "contests/contest";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/addContest", method = RequestMethod.GET)
    public String initAddContest(Model model, Principal principal) {
        LOG.info("\"" + principal.getName() + "\"" + " starts contest creation.");
        Contest c = new Contest();
        c.setHidden(true);
        c.setStatus(Contest.Status.PREPARING);
        model.addAttribute("contestModelForm", c);
        return "contests/contestCreate";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/addContest", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidationResponse addContest(@ModelAttribute("contestModelForm") Contest contestAddForm,
                                  Principal principal, BindingResult result
    ) {
        //Json response object:
        ValidationResponse res = new ValidationResponse();
        //add logger info:
        LOG.info("\"" + principal.getName() + "\"" + " tries to " + (contestAddForm.getId() == 0 ? "create new" : "edit an existing") + " contest");

        //if Contest has id- check if user has access to modify it:
        if (contestAddForm.getId() != 0) {
            if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), contestAddForm.getId())) {
                res.setStatus("FAIL");
                LinkedList<ErrorMessage> linkedList = new LinkedList<ErrorMessage>();
                linkedList.add(new ErrorMessage("denied", null));
                res.setErrorMessageList(linkedList);
                LOG.info("\"" + principal.getName() + "\"" + " have no access to edit an existing contest");
                return res;
            }
        }

        //validation :
        contestValidator.validate(contestAddForm, result);
        //if there are errors in field input:
        if (result.hasErrors()) {
            LOG.info("Contest validation failed.");
            res.setStatus("FAIL");
            List<FieldError> allErrors = result.getFieldErrors();
            List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
            for (FieldError objectError : allErrors) {
                errorMesages.add(new ErrorMessage(objectError.getField(), messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
            }
            //set all errrors:
            res.setErrorMessageList(errorMesages);
        } else {
            LOG.info("Contest validation passed.");
            res.setStatus("SUCCESS");
            //set users:
            if (contestAddForm.getId() != 0) {
                Contest exist = contestService.getContestByID(contestAddForm.getId());
                //set users:
                contestAddForm.setUsers(exist.getUsers());
                //set tasks:
                contestAddForm.setTasks(exist.getTasks());
                //update contest:
                contestService.mergeContest(contestAddForm);
            } else {
                //set author:
                User author = userService.getUserByUsername(principal.getName());
                List<User> list = new LinkedList<User>();
                list.add(author);
                contestAddForm.setUsers(list);
                contestService.addContest(contestAddForm);
            }
            LOG.info("Contest successfully saved to DB.");
            //set generated id to JSON response:
            res.setResult(String.valueOf(contestAddForm.getId()));
        }
        return res;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/editContest", method = RequestMethod.GET, params = "id")
    public String initEditContest(@RequestParam("id") int id, Principal principal, Model model) {
        if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), id)) {
            return "access/accessDenied403";
        }
        //get Contest by id:
        Contest editContest = contestService.getContestByID(id);
        //add contest to view and forward it:
        model.addAttribute("contestModelForm", editContest);
        return "contests/contestCreate";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @RequestMapping(value = "/getContestUsersList.do", method = RequestMethod.GET)
    public
    @ResponseBody
    LinkedList<UserJson> getusers(@RequestParam("contestId") int contestId, Principal principal
    ) {
        //create UserJson result list:
        LinkedList<UserJson> result = new LinkedList<UserJson>();
        //get ContestId Model:
        Contest c = null;
        String author = null;
        //if it is not new Contest- get Users for this Contest:
        if (contestId != 0) {
            c = contestService.getContestByID(contestId);
        } else {
            //else - set author:
            author = principal.getName();
        }
        //get List of all Teacher users
        List<User> teachers = userService.getUsersByRole("ROLE_TEACHER");
        for (User u : teachers) {
            UserJson newUser = new UserJson();
            newUser.setId(u.getUserId());
            newUser.setName(u.getDisplayName());
            newUser.setDepartment(u.getDepartment());
            if (c != null) {
                if (c.getUsers().contains(u)) {
                    newUser.setChoosed(true);
                } else {
                    newUser.setChoosed(false);
                }
            } else {
                if (author.equals(u.getUsername())) {
                    newUser.setChoosed(true);
                } else {
                    newUser.setChoosed(false);
                }
            }
            result.add(newUser);
        }
        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @RequestMapping(value = "/setContestUsers.do", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidationResponse setContestUsers(@RequestParam("contestId") int contestId, Principal principal,
                                       @RequestParam("userIds[]") int[] userIds
    ) {
        if (!((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), contestId)) {
                throw new ContestAccessDenied(principal.getName() + " tried to edit Contest. Access denied.");
            }
        }
        //JSON response class:
        ValidationResponse result = new ValidationResponse();
        //get Contest:
        Contest c = contestService.getContestByID(contestId);
        //if first element is "-1" - so there are no users in array.
        if (!(userIds[0] == -1)) {
            //for each userId get user from db:
            List<User> usersList = new LinkedList<User>();
            for (int id : userIds) {
                //get user from DB:
                usersList.add(userService.getUserById(id));
            }
            //set lsit of users to contest:
            c.setUsers(usersList);
            //update db:
            contestService.mergeContest(c);
            LOG.info("\"" + principal.getName() + "\"" + " successfully saved user access list for contest (id=" + contestId + ")");
        } else {
            //set to log:
            LOG.info("\"" + principal.getName() + "\" have deleted all users from access list for contest (id=" + contestId + ")");
            c.setUsers(null);
            contestService.mergeContest(c);
        }
        //return SUCCESS status:
        result.setStatus("SUCCESS");
        return result;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/stopContestPrepare.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ValidationResponse stopContestPreparing(@RequestParam("id") int contestId, Principal principal) {
        if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), contestId)) {
            throw new ContestAccessDenied(principal.getName() + " tried to edit Contest. Access denied.");
        }

        ValidationResponse result = new ValidationResponse();
        Contest contest = contestService.getContestByID(contestId);
        //validate task size:
        if (contest.getTasks() == null || contest.getTasks().size() == 0) {
            result.setStatus("TASK_SIZE");
            return result;
        }
        //validate Contest start date:
        if (contest.getStarts() == null || contest.getDuration() == null) {
            result.setStatus("FAIL_EMPTY");
            return result;
        }
        //if start date less than now:
        if (contest.getStarts().getTime() < (System.currentTimeMillis())) {
            result.setStatus("FAIL_STARTS");
            return result;
        } else {
            contest.setHidden(false);
            contest.setStatus(Contest.Status.RELEASE);
            contestService.updateContest(contest);
            scheduleService.registerStartContestSchedule(contest);
            scheduleService.registerEndContestSchedule(contest);
            result.setStatus("SUCCESS");
            LOG.info("Contest (id=" + contest.getId() + ") changed status to RELEASE");
        }
        return result;
    }
}
