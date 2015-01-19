/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.json.ContestUserRecieve;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.validators.ContestValidator;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.UserJson;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Роман
 */
@Controller
public class ContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/getContests")
    public String getContests(Model model, Principal principal) {
        List<Integer> editableContestIndexes = new LinkedList<Integer>();
        //get all avaible  сontests from DB:
        List<Contest> all = contestService.getContests();
        //forward all all avaible contests to view:
        model.addAttribute("contests", all);
        //if principal consist:
        if (principal != null) {
            //get user:
            UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            //if user has teacher Role:
            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
                //get User entity from db:
                User teacher = userService.getUserByUsername(currentUser.getUsername());
                //getContests for this teacher:
                if (teacher.getContest() != null) {
                    if (teacher.getContest().size() > 0) {
                        for (Contest c : teacher.getContest()) {
                            //set inexes of Contest which Teacher can edit:
                            editableContestIndexes.add(c.getId());
                        }
                        model.addAttribute("editContestIndexes", editableContestIndexes);
                    }
                }
            }
        }
        return "contest";
    }

    @RequestMapping(value = "/addContest", method = RequestMethod.GET)
    public String initAddContest(Model model, Principal principal) {
        boolean accessed = false;
        //check if teacher:
        if (principal != null) {
            //get user:
            UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            //if user has teacher Role:
            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
                accessed = true;
            }
        }
        //if user haven't acces to contest creation:
        if (accessed == false) {
            model.addAttribute("result", "access denied");
            return "result";
        }
        //Create new Contest and forward it to contestCreate.jsp
        model.addAttribute("contestModelForm", new Contest());
        return "contestCreate";
    }

    @RequestMapping(value = "/editContest", method = RequestMethod.GET, params = "id")
    public String initEditContest(Model model, @RequestParam("id") int id, Principal principal) {
        //security:
        //get user:
        boolean accessed = false;
        if (principal != null) {
            UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            //if user has teacher Role:
            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
                //get User entity from db:
                User teacher = userService.getUserByUsername(currentUser.getUsername());
                if (teacher.getContest() != null) {
                    if (teacher.getContest().size() > 0) {
                        for (Contest c : teacher.getContest()) {
                            if (c.getId() == id) {
                                accessed = true;
                            }
                        }
                    }
                }
            }
        }
        //user doesn't passed valdiation-forward to access denied.
        if (accessed == false) {
            model.addAttribute("result", "access denied");
            return "result";
        }

        //get Contest by id:
        Contest editContest = contestService.getContestByID(id);
        model.addAttribute("contestModelForm", editContest);
        return "contestCreate";
    }

    @RequestMapping(value = "/addContest", method = RequestMethod.POST)
    public @ResponseBody
    ValidationResponse addContest(@ModelAttribute("contestModelForm") Contest contestAddForm,
            Principal principal, BindingResult result
    ) {
        //Json response object:
        ValidationResponse res = new ValidationResponse();
        //validation :
        new ContestValidator().validate(contestAddForm, result);
        //if there are errors in field input:
        if (result.hasErrors()) {
            res.setStatus("FAIL");
            List<FieldError> allErrors = result.getFieldErrors();
            List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
            for (FieldError objectError : allErrors) {
                errorMesages.add(new ErrorMessage(objectError.getField(), messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
            }
            //set all errrors:
            res.setErrorMessageList(errorMesages);
        } else {
            res.setStatus("SUCCESS");
            //set users:
            if (contestAddForm.getId() != 0) {
                Contest exist = contestService.getContestByID(contestAddForm.getId());
                contestAddForm.setUsers(exist.getUsers());
            } else {
                //set author:
                User author = userService.getUserByUsername(principal.getName());
                List<User> list = new LinkedList<User>();
                list.add(author);
                contestAddForm.setUsers(list);
            }
            //update contest:
            contestService.updateContest(contestAddForm);
            //set generated id to JSON response:
            res.setResult(String.valueOf(contestAddForm.getId()));
        }
        return res;
    }

    //get taskModalForm by Json request:
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

    @RequestMapping(value = "/getContestUsersList.do", method = RequestMethod.GET)
    public @ResponseBody
    LinkedList<UserJson> getusers(@RequestParam("contestId") int contestId, Principal principal
    ) {
        //create UserJson result list:
        LinkedList<UserJson> result = new LinkedList<UserJson>();
        //get ContestId Model:
        Contest c = null;
        String author = null;
        if (contestId != 0) {
            c = contestService.getContestByID(contestId);
        } else {
            //set author:
            author = principal.getName();
        }
        //get List of all Teacher users
        List<User> teachers = userService.getUsersByRole("ROLE_TEACHER");
        for (User u : teachers) {
            UserJson newUser = new UserJson();
            newUser.setId(u.getUserId());
            newUser.setName(u.getDisplayName());
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
        author = null;
        c = null;
        return result;
    }

    @RequestMapping(value = "/setContestUsers.do", method = RequestMethod.POST)
    public @ResponseBody
    ValidationResponse setContestUsers(@RequestBody ContestUserRecieve users
    ) {
        //JSON response class:
        ValidationResponse result = new ValidationResponse();
        //0. get Contest:
        Contest c = contestService.getContestByID(users.getContestId());
        //for each userId get user from db:
        List<User> usersList = new LinkedList<User>();
        for (Integer id : users.getUserIds()) {
            //get user from DB:
            usersList.add(userService.getUserById(id));
        }
        //set lsit of users to contest:
        c.setUsers(usersList);
        //update db:
        contestService.mergeContest(c);
        //return SUCCESS status:
        result.setStatus("SUCCESS");
        return result;
    }

}
