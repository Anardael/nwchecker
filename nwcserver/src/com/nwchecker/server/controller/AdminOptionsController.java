package com.nwchecker.server.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.validators.RolesDescriptionValidator;
import com.nwchecker.server.validators.UserEditValidator;

/**
*
* @author Станіслав
*/
@Controller
public class AdminOptionsController {

	private static final Logger LOG = Logger.getLogger(AdminOptionsController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserEditValidator userEditValidator;
	
	@Autowired
	private RolesDescriptionValidator rolesDescriptionValidator;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Principal principal) {
		LOG.info("\"" + principal.getName() + "\" tries to access administrator page(users view).");
		LOG.info("\"" + principal.getName() + "\" have access to administrator page(users view).");
		return "adminOptions/users";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public @ResponseBody String getUsers(Principal principal) {
		LOG.info("\"" + principal.getName() + "\" tries to access users list.");
		List<User> users = userService.getUsers();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String jsonData = gson.toJson(users.toArray());
		LOG.info("\"" + principal.getName() + "\" received users list.");
		return jsonData;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/userEdit", method = RequestMethod.GET)
	public String user(@RequestParam(value = "Username", required = false) String username, 
						Model model, Principal principal) {
		if (username == null) {
			return "adminOptions/users";
		}
		LOG.info("\"" + principal.getName() + "\" tries to edit user \"" + username + "\".");
		User user = userService.getUserByUsername(username);
		user.setPassword("");
		model.addAttribute("userData", user);
		LOG.info("\"" + principal.getName() + "\" have access to user editing (" + username + ").");
		return "adminOptions/userEdit";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	public String changeUser(@ModelAttribute("userData") User userData, 
							 @RequestParam("rolesDesc") String rolesDesc,
							 BindingResult result, Model model, Principal principal) {
		LOG.info("Received new account data for user \"" + userData.getUsername() + "\".");
		User user = userService.getUserByUsername(userData.getUsername());
		
		userEditValidator.validate(userData, result);
		rolesDescriptionValidator.validate(rolesDesc, result);
		if (result.hasErrors()) {
			LOG.warn("User \"" + userData.getUsername() + "\" data validation failed!");
			model.addAttribute("roles", user.getRoles());
			return "adminOptions/userEdit";
		}
		LOG.info("User \"" + userData.getUsername() + "\" data validation passed.");
		
		setNewUserPassword(user, userData.getPassword());
		user.setDisplayName(userData.getDisplayName());
		user.setEmail(userData.getEmail());
		setNewUserRoles(user, rolesDesc);
		user.setDepartment(userData.getDepartment());
		user.setInfo(userData.getInfo());		
		userService.updateUser(user);
		LOG.info("User \"" + userData.getUsername() + "\" successfully updated in DB.");
		LOG.info("User \"" + user.getUsername() + "\" edited by \"" + principal.getName() + "\".");
		return "redirect:admin.do";
	}
	
	private void setNewUserPassword(User user, String newPassword) {
		if (!newPassword.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			newPassword = encoder.encode(newPassword);
			user.setPassword(newPassword);
			LOG.info("User \"" + user.getUsername() + "\" password changed.");
		}
	}
	//TODO
	private void setNewUserRoles(User user, String rolesDesc) {	
		if (user.getRoles() == null) {
			user.setRoles(new HashSet<Role>());
		}
		
		if ((user.hasRole("ROLE_ADMIN")) && (!rolesDesc.contains("ROLE_ADMIN"))) {
			userService.deleteUserRole(user, "ROLE_ADMIN");
			LOG.info("User \"" + user.getUsername() + "\" lost ADMIN rights.");
		}
		if ((user.hasRole("ROLE_TEACHER")) && (!rolesDesc.contains("ROLE_TEACHER"))) {
			userService.deleteUserRole(user, "ROLE_TEACHER");
			LOG.info("User \"" + user.getUsername() + "\" lost ADMIN rights.");
		}
		if ((user.hasRole("ROLE_USER")) && (!rolesDesc.contains("ROLE_USER"))) {
			userService.deleteUserRole(user, "ROLE_USER");
			LOG.info("User \"" + user.getUsername() + "\" lost ADMIN rights.");
		}

		if ((!user.hasRole("ROLE_ADMIN")) && (rolesDesc.contains("ROLE_ADMIN"))) {
			user.addRoleAdmin();
			LOG.info("User \"" + user.getUsername() + "\" acquired ADMIN rights.");
		}
		if ((!user.hasRole("ROLE_TEACHER")) && (rolesDesc.contains("ROLE_TEACHER"))) {
			user.addRoleTeacher();
			LOG.info("User \"" + user.getUsername() + "\" acquired TEACHER rights.");
		}
		if ((!user.hasRole("ROLE_USER")) && (rolesDesc.contains("ROLE_USER"))) {
			user.addRoleUser();
			LOG.info("User \"" + user.getUsername() + "\" acquired USER rights.");
		}
		if (user.getRoles().isEmpty()) {
			user.addRoleUser();
			LOG.warn("User \"" + user.getUsername() + "\" don't has any role!");
			LOG.info("User \"" + user.getUsername() + "\" acquired USER rights.");
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("username") String username, Principal principal) {
		LOG.info("\"" + principal.getName() + "\" tries to delete user \"" + username + "\".");
		if (!principal.getName().equals(username)) {
			userService.deleteUserByName(username);
			LOG.info("User \"" + username + "\" successfully deleted from DB.");
			LOG.info("User \"" + username + "\" deleted by \"" + principal.getName() + "\".");
		} else {
			LOG.warn("User cann't delete themself!");
		}
		return "redirect:admin.do";
	}
}
