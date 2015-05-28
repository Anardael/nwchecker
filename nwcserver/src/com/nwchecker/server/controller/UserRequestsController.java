package com.nwchecker.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.json.wrapper.FilteredResultProvider;
import com.nwchecker.server.json.wrapper.MorphedResult;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * <h1>User Requests Controller</h1> This spring controller contains mapped
 * methods, that allows to work with users requests.
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 * @since 2015-01-25
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

	/**
	 * This mapped method used to return page where administrator can view list
	 * of users requests.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return <b>userRequests.jsp</b> Returns page where admin can view list of
	 *         users requests
	 */
	@Link(label = "userRequests.caption", family = "adminOptions", parent = "")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/userRequests", method = RequestMethod.GET)
	public String userRequests(Principal principal, Model model) {
		LOG.info("\"" + principal.getName()
				+ "\" have access to administrator page(users requests).");
		model.addAttribute("pageName", "userRequests");
		return "nwcserver.adminOptions.userRequests";
	}

	/**
	 * This mapped method used to return list of users requests in JSON format.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return <b>JSON</b> Returns <b>list of users requests</b> in JSON
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getUsersWithRequests", method = RequestMethod.GET)
	@ResponseBody
	public String getUsersWithRequests(Principal principal) {
		LOG.info("\"" + principal.getName()
				+ "\" tries to access users requests list.");
		List<User> users = userService.getUsersWithRequests();
		List<MorphedResult<User>> morphedUserList = new LinkedList<MorphedResult<User>>();
		for (User user : users) {
			MorphedResult<User> morphedUser = new MorphedResult<User>(user);
			morphedUser.addExpansionData("checked", false);
			morphedUserList.add(morphedUser);
		}
		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.setFilters(new FilteredResultProvider());
		jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String result = null;
		try {
			result = jsonMapper.writeValueAsString(morphedUserList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("\"" + principal.getName()
				+ "\" received users requests list.");
		return result;
	}

	/**
	 * This mapped method used to accept some of users requests.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param usersUsername
	 *            Usernames of users whose requests will be accepted
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return Status "SUCCESS"
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/acceptUserRequests", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse acceptUserRequests(
			@RequestParam("arrayUsersUsername[]") String[] usersUsername,
			Principal principal) {
		LOG.info("\"" + principal.getName()
				+ "\" confirmed requests for role \"Teacher\".");
		for (String username : usersUsername) {
			User user = userService.getUserByUsername(username);
			UserRequest wantRoleTeacher = new UserRequest(user,
					"WANT_ROLE_TEACHER");
			for (UserRequest request : user.getRequests()) {
				if (request.equals(wantRoleTeacher)) {
					userService.deleteRequest(user, request);
					user.addRole("ROLE_TEACHER");
				}
			}
			userService.updateUser(user);
			LOG.info("Request from \"" + user.getUsername()
					+ "\" for role \"Teacher\" is accepted.");
		}
		return ValidationResponse.createValidationResponse("Success");
	}

	/**
	 * This mapped method used to deny some of users requests.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param usersUsername
	 *            Usernames of users whose requests will be denied
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return Status "SUCCESS"
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/declineUserRequests", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse declineUserRequests(
			@RequestParam("arrayUsersUsername[]") String[] usersUsername,
			Principal principal) {
		LOG.info("\"" + principal.getName()
				+ "\" declined requests for role \"Teacher\".");
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
			LOG.info("Request from \"" + user.getUsername()
					+ "\" for role \"Teacher\" is declined.");
		}
		return ValidationResponse.createValidationResponse("Success");
	}
}
