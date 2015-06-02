package com.nwchecker.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.exceptions.ContestAccessDenied;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.json.wrapper.FilteredResultProvider;
import com.nwchecker.server.json.wrapper.MorphedResult;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.TypeContest;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.*;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>Contest Controller</h1>
 * This spring controller contains mapped methods, that allows
 * to read, create and edit contests in database.
 * <p>
 * <b>Note:</b>Only teacher allows to create and change contests.
 * Other users can only view contests information
 *
 * @author Roman Zayats
 * @version 1.0
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

    @Autowired
    private TypeContestService typeContestService;

    /**
     * This mapped method used to return page with contests list
     * <p>
     *
     * @param model Spring Framework model for this page
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return <b>contest.jsp</b> Returns page when user can view contests
     */
    @Link(label="contest.caption", family="contests", parent = "")
    @RequestMapping("/getContests")
    public String getContests(Model model, Principal principal) {

        if(principal == null){
            model.addAttribute("pageName", "contest");

            return "nwcserver.contests.show";
        }

        User user = userService.getUserByUsername(principal.getName());

        if (((UsernamePasswordAuthenticationToken) principal).getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
            List<String> editableContestIndexes = new LinkedList<String>();
            //getContests which user can edit:
            if ((user.getContest() != null) && (user.getContest().size() > 0)) {
                for (Contest c : user.getContest()) {
                    if (c.getStatus().equals(Contest.Status.PREPARING)
                            || c.getStatus().equals(Contest.Status.ARCHIVE)) {
                        // set index for view
                        if (c.getStatus().equals(Contest.Status.PREPARING)) {
                            editableContestIndexes.add("index" + c.getId()
                                    + "index");
                        }
                    }
                }
            }
            model.addAttribute("editContestIndexes", editableContestIndexes);
            // set usernames for editing contests:
            model.addAttribute("nowContestEdits", contestEditWatcherService.getNowEditsMap());
        }

        model.addAttribute("pageName", "contest");

        return "nwcserver.contests.show";
    }

    /**
     * This mapped method used to return page when teacher
     * can create new contest.
     * <p>
     * <b>Note:</b>Only TEACHER has rights to use this method.
     *
     * @param model Spring Framework model for this page
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return <b>contestCreate.jsp</b> Returns page where user can create new contest
     */
    @Link(label="contestCreate.caption", family="contests", parent = "contest.caption")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/addContest", method = RequestMethod.GET)
    public String initAddContest(Model model, Principal principal) {
    	model.addAttribute("pageName","contest");
        LOG.info("\"" + principal.getName() + "\"" + " starts contest creation.");
        Contest c = new Contest();
        c.setHidden(true);
        c.setStatus(Contest.Status.PREPARING);
        model.addAttribute("contestModelForm", c);

        List<TypeContest> typeContestList= typeContestService.getAllTypeContest();
        model.addAttribute("typeContestList", typeContestList);

        return "nwcserver.contests.create";
    }

    /**
     * This mapped method used to receive new contest data and
     * create new contest in database.
     * <p>
     * <b>Note:</b>Only TEACHER has rights to use this method.
     *
     * @param contestAddForm Data set that contains information about new contest
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param result General spring interface that used in data validation
     * @return Returns "SUCCESS" status if <b>success</b>.
     *         Returns "FAIL" status if <b>fails</b>.
     */
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/addContest", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidationResponse addContest(@ModelAttribute("contestModelForm") Contest contestAddForm,
                                  Principal principal, BindingResult result
    ) {
        //Json response object:
        ValidationResponse res = ValidationResponse.createValidationResponse();
        //add logger info:
        LOG.info("\"" + principal.getName() + "\"" + " tries to " + (contestAddForm.getId() == 0 ? "create new" : "edit an existing") + " contest");

        //if Contest has id- check if user has access to modify it:
        if (contestAddForm.getId() != 0) {
            if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), contestAddForm.getId())) {
                res.setStatus("FAIL");
                LinkedList<ErrorMessage> linkedList = new LinkedList<ErrorMessage>();
                linkedList.add(ErrorMessage.createErrorMessage("denied", null));
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
            List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
            for (FieldError objectError : allErrors) {
                errorMessages.add(ErrorMessage.createErrorMessage(objectError.getField(), messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
            }
            //set all errors:
            res.setErrorMessageList(errorMessages);
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

    /**
     * This mapped method used to return page where teacher
     * can edit selected contest.
     * <p>
     * <b>Note:</b>Only TEACHER has rights to use this method.
     *
     * @param id ID of contest that will be edited
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param model Spring Framework model for this page
     * @return <b>contestCreate.jsp</b> Returns page where teacher can edit contest
     */
    @Link(label="contestEdit.caption", family="contests", parent = "contest.caption")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/editContest", method = RequestMethod.GET, params = "id")
    public String initEditContest(@RequestParam("id") int id, Principal principal, Model model) {
        /*if (!contestService.checkIfUserHaveAccessToContest(principal.getName(), id)) {
            return "nwcserver.403";
        }*/
        //get Contest by id:
        Contest editContest = contestService.getContestByID(id);
        //add contest to view and forward it:
        model.addAttribute("contestModelForm", editContest);

        List<TypeContest> typeContestList= typeContestService.getAllTypeContest();
        model.addAttribute("typeContestList", typeContestList);
        model.addAttribute("pageName", "contest");
        return "nwcserver.contests.create";
    }

    /**
     * This mapped method used to return list of teachers that
     * have rights to edit this contest.
     * <p>
     * <b>Note:</b>Only TEACHERs and ADMINs have rights to use this method.
     *
     * @param contestId ID of selected contest
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return <b>JSON</b> Returns <b>List of Teachers</b> than can edit this contest
     */
   
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @RequestMapping(value = "/getContestUsersList.do", method = RequestMethod.GET)
    public @ResponseBody String getUsers(
            @RequestParam("contestId") int contestId, Principal principal) {
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
		List<MorphedResult<User>> morphedTeachers = new LinkedList<MorphedResult<User>>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setFilters(new FilteredResultProvider());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		for (User user : teachers) {
			MorphedResult<User> morphedTeacher = new MorphedResult<User>(user);
			morphedTeacher.excludeAttribute("roles");
			morphedTeacher.excludeAttribute("username");
			morphedTeacher.excludeAttribute("email");
			morphedTeacher.excludeAttribute("info");
			morphedTeacher.excludeAttribute("requests");
			if (c != null) {
				if (c.getUsers().contains(user)) {
					morphedTeacher.addExpansionData("chose", true);
				} else {
					morphedTeacher.addExpansionData("chose", false);
				}
			} else {
				if (author.equals(user.getUsername())) {
					morphedTeacher.addExpansionData("chose", true);
				} else {
					morphedTeacher.addExpansionData("chose", false);
				}
			}
			morphedTeachers.add(morphedTeacher);
		}
		String response = null;
		try {
			response = mapper.writeValueAsString(morphedTeachers);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch: Change to logging
			e.printStackTrace();
		}
		return response;
/*        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
			String dat = mapper.writerWithView(UserView.ViewUsers.class).writeValueAsString(teachers);
			System.out.println(dat);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        for (User u : teachers) {
            UserJson newUser = UserJson.createUserJson();
            newUser.setId(u.getUserId());
            newUser.setName(u.getDisplayName());
            newUser.setDepartment(u.getDepartment());
            if (c != null) {
                if (c.getUsers().contains(u)) {
                    newUser.setChose(true);
                } else {
                    newUser.setChose(false);
                }
            } else {
                if (author.equals(u.getUsername())) {
                    newUser.setChose(true);
                } else {
                    newUser.setChose(false);
                }
            }
            result.add(newUser);
        }
        return result;*/
    }

    /**
     * This mapped method used to set list of teachers that
     * have rights to edit this contest.
     * <p>
     * <b>Note:</b>Only TEACHERs and ADMINs have rights to use this method.
     *
     * @param contestId ID of selected contest
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param userIds List of users IDs that will have rights to edit contest
     * @return Returns "SUCCESS" status if <b>success</b>.
     *         Returns "FAIL" status if <b>fails</b>.
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @RequestMapping(value = "/setContestUsers.do", method = RequestMethod.POST)
    public @ResponseBody ValidationResponse setContestUsers(
            @RequestParam("contestId") int contestId, Principal principal,
            @RequestParam("userIds[]") int[] userIds) {
        if (!((UsernamePasswordAuthenticationToken) principal).getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            if (!contestService.checkIfUserHaveAccessToContest(
                    principal.getName(), contestId)) {
                throw new ContestAccessDenied(principal.getName()
                        + " tried to edit Contest. Access denied.");
            }
        }
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
            //set list of users to contest:
            c.setUsers(usersList);
            //update db:
            contestService.mergeContest(c);
            LOG.info("\"" + principal.getName() + "\""
                    + " successfully saved user access list for contest (id="
                    + contestId + ")");
        } else {
            // set to log:
            LOG.info("\""
                    + principal.getName()
                    + "\" have deleted all users from access list for contest (id="
                    + contestId + ")");
            c.setUsers(null);
            contestService.mergeContest(c);
        }
        //return SUCCESS status (response in JSON):
        return ValidationResponse.createValidationResponse("SUCCESS");
    }

    /**
     * This mapped method used to change contest status to "RELEASE"
     * <p>
     * <b>Note:</b>Only TEACHER has rights to use this method.
     *
     * @param contestId ID of selected contest
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return Returns status "TASK_SIZE", "FAIL_EMPTY", "FAIL_STARTS" or "SUCCESS"
     */
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/stopContestPrepare.do", method = RequestMethod.GET)
    public @ResponseBody ValidationResponse stopContestPreparing(
            @RequestParam("id") int contestId, Principal principal) {
        if (!contestService.checkIfUserHaveAccessToContest(principal.getName(),
                contestId)) {
            throw new ContestAccessDenied(principal.getName()
                    + " tried to edit Contest. Access denied.");
        }

        ValidationResponse result = ValidationResponse
                .createValidationResponse();
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
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM,yyyy HH:mm");    
        Date resultdate = new Date(yourmilliseconds);
        System.out.println(sdf.format(resultdate));
        System.out.println(sdf.format(contest.getStarts()));
        System.out.println(contest.getStarts().getTime()-yourmilliseconds);
        //if start date less than now:
        if (contest.getStarts().getTime() < (System.currentTimeMillis())) {
            result.setStatus("FAIL_STARTS");
            return result;
        } else {
            contest.setHidden(false);
            contest.setStatus(Contest.Status.RELEASE);
            contestService.updateContest(contest);
            scheduleService.refresh();
            result.setStatus("SUCCESS");
            LOG.info("Contest (id=" + contest.getId()
                    + ") changed status to RELEASE");
        }
        return result;
    }
    
    @Link(label="contest.caption", family="contests", parent = "")
    @RequestMapping("/getContestsByStatus")
    public String getContestsByStatus(@RequestParam (defaultValue="1") int page,@RequestParam (defaultValue="3") int pageSize,@RequestParam("status") String status,
                                      Model model, Principal principal) {
        // get сontests by status from DB:
        Long count = contestService.getPageCount(Contest.Status.stringToStatus(status), pageSize);
        model.addAttribute("currentPage" , page);
        model.addAttribute("pageCount" ,count );

        List<Contest> contestByStatus = contestService.getPagedContests(Contest.Status.stringToStatus(status), pageSize, page);
        // get unhidden contests:
        List<Contest> unhidden = new LinkedList<Contest>();
        for (Contest c : contestByStatus) {
            if (!c.isHidden()) {
                unhidden.add(c);
            }
        }
        if (principal == null) {
            // return all "unhidden" contests:
            model.addAttribute("contests", unhidden);
            return "nwcserver.contests.show";
        }

        User user = userService.getUserByUsername(principal.getName());

        if (((UsernamePasswordAuthenticationToken) principal).getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
            List<String> editableContestIndexes = new LinkedList<String>();
            // getContests which user can edit:
            if ((user.getContest() != null) && (user.getContest().size() > 0)) {
                for (Contest c : user.getContest()) {
                    if (c.getStatus().equals(Contest.Status.PREPARING)
                            || c.getStatus().equals(Contest.Status.ARCHIVE)) {
                        // set index for view
                        if (c.getStatus().equals(Contest.Status.PREPARING)) {
                            editableContestIndexes.add("index" + c.getId()
                                    + "index");
                        }
                        // add Contest to unhidden list:
                        if (c.isHidden()) {
                            unhidden.add(c);
                        }
                    }
                }
            }
            model.addAttribute("editContestIndexes", editableContestIndexes);
            // set usernames for editing contests:
            model.addAttribute("nowContestEdits",
                    contestEditWatcherService.getNowEditsMap());
        }
        model.addAttribute("contests", unhidden);
        return "nwcserver.contests.show";
    }

    @RequestMapping(value = "/contestListJson", method = RequestMethod.GET)
    public @ResponseBody List<Contest> getContestListJson() {
        return contestService.getContests();
    }

}
