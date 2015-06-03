package com.nwchecker.server.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.json.JTableResponseList;
import com.nwchecker.server.json.JsonViews;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.validators.RolesDescriptionValidator;
import com.nwchecker.server.validators.UserEditValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashSet;

/**
 * <h1>Admin Options Controller</h1> This spring controller contains mapped
 * methods, that implements main administrator options.
 * <p>
 * <b>Note:</b>Only administrator allows to change users profile data.
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-01-08
 */
@Controller
public class AdminOptionsController {

	private static final Logger LOG = Logger
			.getLogger(AdminOptionsController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserEditValidator userEditValidator;

	@Autowired
	private RolesDescriptionValidator rolesDescriptionValidator;

	/**
	 * This mapped method used to return page where administrator can watch
	 * profile data of all users in system.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return <b>users.jsp</b> Returns page with list of users
	 */
	@Link(label = "admin.users.caption", family = "adminOptions", parent = "")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Principal principal, Model model) {
		model.addAttribute("pageName", "admin");
		LOG.info("\"" + principal.getName()
				+ "\" tries to access administrator page(users view).");
		LOG.info("\"" + principal.getName()
				+ "\" have access to administrator page(users view).");
		return "nwcserver.adminOptions.users";
	}

	/**
	 * This mapped method used to return general data about all users in JSON
	 * format.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return <b>JSON</b> Returns <b>List of Users</b> in JSON format
	 */
	@JsonView(JsonViews.ViewUsersAdmin.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getUsers.do", method = RequestMethod.GET)
	public @ResponseBody JTableResponseList getUsers(
			Principal principal,
			@RequestParam("offset") int startIndex,
			@RequestParam("limit") int pageSize,
			@RequestParam(required = false, value = "sort") String sortingColumn,
			@RequestParam(required = false, value = "order") String sortingOrder,
			@RequestParam(required = false, value = "search") String filter) {
		LOG.info("\"" + principal.getName() + "\" tries to access users list.");
		JTableResponseList jTableResponse = new JTableResponseList(
				userService.getPagedUsers(startIndex, pageSize, sortingColumn,
						sortingOrder, filter),
				userService.getRecordCount(filter));
		return jTableResponse;
	}

	/**
	 * This mapped method used to return page where administrator can edit
	 * profile data of selected user.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param username
	 *            Username of selected user
	 * @param model
	 *            Spring Framework model for this page
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return <b>userEdit.jsp</b> Returns page that allows to change user data
	 */
	@Link(label = "admin.userEdit.caption", family = "adminOptions", parent = "admin.users.caption")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/userEdit", method = RequestMethod.GET)
	public String user(@RequestParam(value = "Username") String username,
			Model model, Principal principal) {
		LOG.info("\"" + principal.getName() + "\" tries to edit user \""
				+ username + "\".");
		User user = userService.getUserByUsername(username);
		user.setPassword("");
		model.addAttribute("userData", user);
		LOG.info("\"" + principal.getName()
				+ "\" have access to user editing (" + username + ").");
		model.addAttribute("pageName", "admin");
		return "nwcserver.adminOptions.userEdit";
	}

	/**
	 * This mapped method used to receive modified user's data and update this
	 * user in database.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param userData
	 *            Data set that contains new information about user
	 * @param rolesDesc
	 *            String that contains new user roles. Example:
	 *            {@code "ROLE_TEACHER;ROLE_USER;"}
	 * @param result
	 *            General spring interface that used in data validation
	 * @param model
	 *            Spring Framework model for this page
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return Redirects to users list page if <b>success</b>. Shows validation
	 *         errors on page if <b>validation fails</b>.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	public String changeUser(@ModelAttribute("userData") User userData,
			@RequestParam("rolesDesc") String rolesDesc, BindingResult result,
			Model model, Principal principal) {
		LOG.info("Received new account data for user \""
				+ userData.getUsername() + "\".");
		User user = userService.getUserByUsername(userData.getUsername());

		userEditValidator.validate(userData, result);
		rolesDescriptionValidator.validate(rolesDesc, result);
		if (result.hasErrors()) {
			LOG.warn("User \"" + userData.getUsername()
					+ "\" data validation failed!");
			model.addAttribute("roles", user.getRoles());
			return "nwcserver.adminOptions.userEdit";
		}
		LOG.info("User \"" + userData.getUsername()
				+ "\" data validation passed.");

		setNewPassword(user, userData.getPassword());
		user.setDisplayName(userData.getDisplayName());
		user.setEmail(userData.getEmail());
		setNewRoles(user, rolesDesc);
		user.setDepartment(userData.getDepartment());
		user.setInfo(userData.getInfo());
		userService.updateUser(user);
		LOG.info("User \"" + userData.getUsername()
				+ "\" successfully updated in DB.");
		LOG.info("User \"" + user.getUsername() + "\" edited by \""
				+ principal.getName() + "\".");
		return "redirect:admin.do";
	}

	/**
	 * This method used to set new user's password.
	 * <p>
	 *
	 * @param user
	 *            User whose password will be changed
	 * @param newPassword
	 *            String that will be hashed and set as new password
	 */
	private void setNewPassword(User user, String newPassword) {
		if (!newPassword.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			newPassword = encoder.encode(newPassword);
			user.setPassword(newPassword);
			LOG.info("User \"" + user.getUsername() + "\" password changed.");
		}
	}

	/**
	 * This method used to set new user's roles.
	 * <p>
	 * <b>Note:</b>If user already has required role - role will not be changed
	 * in database.
	 *
	 * @param user
	 *            User whose roles will be changed
	 * @param rolesDesc
	 *            String that contains new user roles. Example:
	 *            {@code "ROLE_TEACHER;ROLE_USER;"}
	 */
	private void setNewRoles(User user, String rolesDesc) {
		if (user.getRoles() == null) {
			user.setRoles(new HashSet<Role>());
		}

		Role[] userRoles = user.getRoles().toArray(
				new Role[user.getRoles().size()]);
		for (Role role : userRoles) {
			if (!rolesDesc.contains(role.getRole())) {
				userService.deleteUserRole(user, role.getRole());
				LOG.info("User \"" + user.getUsername() + "\" lost "
						+ role.getRole().substring(5) + " rights.");
			}
		}

		String[] roles = { "ROLE_ADMIN", "ROLE_TEACHER", "ROLE_USER" };
		for (String role : roles) {
			if ((!user.hasRole(role)) && (rolesDesc.contains(role))) {
				user.addRole(role);
				LOG.info("User \"" + user.getUsername() + "\" acquired "
						+ role.substring(5) + " rights.");
			}
		}

		if (user.getRoles().isEmpty()) {
			user.addRole("ROLE_USER");
			LOG.warn("User \"" + user.getUsername() + "\" don't has any role!");
			LOG.info("User \"" + user.getUsername()
					+ "\" acquired USER rights.");
		}
	}

	/**
	 * This mapped method used to delete user by username.
	 * <p>
	 * <b>Note:</b>Only ADMIN has rights to use this method.
	 *
	 * @param username
	 *            Username of user that will be deleted from database
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @return Redirects to users list page
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("username") String username,
			Principal principal) {
		LOG.info("\"" + principal.getName() + "\" tries to delete user \""
				+ username + "\".");
		if (!principal.getName().equals(username)) {
			userService.deleteUserByName(username);
			LOG.info("User \"" + username + "\" successfully deleted from DB.");
			LOG.info("User \"" + username + "\" deleted by \""
					+ principal.getName() + "\".");
		} else {
			LOG.warn("User can't delete them self!");
		}
		return "redirect:admin.do";
	}
}
