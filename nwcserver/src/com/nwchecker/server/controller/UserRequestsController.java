package com.nwchecker.server.controller;

import com.nwchecker.server.json.UserRequestsJson;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ReaktorDTR on 25.01.2015.
 */

@Controller
@SessionAttributes("user")
public class UserRequestsController {

	private static final Logger LOG = Logger
			.getLogger(AdminOptionsController.class);

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("userProfileValidator")
	private Validator validator;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setValidator(validator);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/userRequests", method = RequestMethod.GET)
	public String userRequests(Principal principal) {
		LOG.info("\"" + principal.getName()
				+ "\" tries to access administrator page(users requests).");
		LOG.info("\"" + principal.getName()
				+ "\" have access to administrator page(users requests).");
		return "adminOptions/userRequests";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getUsersWithRequests", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRequestsJson> getUsersWithRequests(Principal principal) {
		LOG.info("\"" + principal.getName()
				+ "\" tries to access users requests list.");
		List<User> users = userService.getUsersWithRequests();
		LinkedList<UserRequestsJson> userRequestsJson = new LinkedList<>();
		for (User user : users) {
			UserRequestsJson ur = new UserRequestsJson(user);
			userRequestsJson.add(ur);
		}
		LOG.info("\"" + principal.getName()
				+ "\" received users requests list.");
		return userRequestsJson;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/acceptUserRequests", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse acceptUserRequests(@RequestParam("arrayUsersUsername[]") String[] usersUsername) {
		LOG.info("Accessed \"/acceptUserRequests.do\"");

		for (String username : usersUsername) {
			User user = userService.getUserByUsername(username);
			UserRequest wantRoleTeacher = new UserRequest(user,
					"WANT_ROLE_TEACHER");
			for (UserRequest request : user.getRequests()) {
				if (request.equals(wantRoleTeacher)) {
					userService.deleteRequest(user, request);
					user.addRoleTeacher();
				}
			}
			userService.updateUser(user);
		}
		ValidationResponse result = new ValidationResponse();
		result.setStatus("Success");
		return result;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/declineUserRequests", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse declineUserRequests(@RequestParam("arrayUsersUsername[]") String[] usersUsername) {
		for (String username : usersUsername) {
			User user = userService.getUserByUsername(username);
			UserRequest wantRoleTeacher = new UserRequest(user,
					"WANT_ROLE_TEACHER");
			for (UserRequest request : user.getRequests()) {
				if (request.equals(wantRoleTeacher)) {
					userService.deleteRequest(user, request);
				}
			}
			userService.updateUser(user);
		}
		ValidationResponse result = new ValidationResponse();
		result.setStatus("Success");
		return result;
	}
}
