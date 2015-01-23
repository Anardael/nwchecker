package com.nwchecker.server.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

@Controller
public class AdminOptionsController {

	private static final Logger LOG = Logger.getLogger(ContestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("userEditValidator")
	private Validator	validator;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setValidator(validator);
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Principal principal) {
		UserDetails currentUser = 
				(UserDetails) ((Authentication) principal).getPrincipal();
		LOG.info(currentUser.getUsername() + " try to access administration page");
		GrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
		if (currentUser.getAuthorities().contains(admin)) {
			LOG.info(currentUser.getUsername() + " -Access GRANTED-");
			return "adminOptions/users";
		}
		LOG.warn("Only administrators can access administration pages!");
		LOG.info(currentUser.getUsername() + " -Access DENIED-");
		return "redirect: /index.do";
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public @ResponseBody String getUsers(Principal principal) {
		UserDetails currentUser = 
				(UserDetails) ((Authentication) principal).getPrincipal();
		LOG.info(currentUser.getUsername() + " try to receive users list");
		GrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
		if (currentUser.getAuthorities().contains(admin)) {
			LOG.info(currentUser.getUsername() + " -Access GRANTED-");
			LOG.info("Users list received by " + currentUser.getUsername());
			List<User> users = userService.getUsers();
			Gson gson = 
					new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			return gson.toJson(users.toArray());
		}
		LOG.warn("Only administrators can access users list!");
		LOG.info(currentUser.getUsername() + " -Access DENIED-");
		return "You haven't rights for this operation!";
	}
	
	@RequestMapping(value = "/userEdit", method = RequestMethod.GET)
	public String user(@RequestParam("Username") String username, 
						Model model, Principal principal) {
		UserDetails currentUser = 
				(UserDetails) ((Authentication) principal).getPrincipal();
		LOG.info(currentUser.getUsername() + " try to acces user edit page");
		GrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
		if (currentUser.getAuthorities().contains(admin)) {
			LOG.info(currentUser.getUsername() + " -Access GRANTED-");
			User user = userService.getUserByUsername(username);
			user.setPassword("");
			model.addAttribute("userData", user);
			return "adminOptions/userEdit";
		}
		LOG.warn("Only administrators can access administration pages!");
		LOG.info(currentUser.getUsername() + " -Access DENIED-");
		return "redirect: /index.do";
	}
	
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	public String changeUser(@ModelAttribute("userData") @Validated User userData, 
							BindingResult result, Principal principal) {
		UserDetails currentUser = 
				(UserDetails) ((Authentication) principal).getPrincipal();
		LOG.info(currentUser.getUsername() + " try to change user " + userData.getUsername());
		GrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
		if (currentUser.getAuthorities().contains(admin)) {
			LOG.info(currentUser.getUsername() + " -Access GRANTED-");
			LOG.info("User can be changed");
			if (result.hasErrors()) {
				LOG.warn("Verification failed!");
				return "/adminOptions/userEdit";
			}
			User user = userService.getUserByUsername(userData.getUsername());
			if (!user.getRoles().isEmpty()) {
				user.setRoles(null);
				userService.deleteUserRoles(user);
			}
			user = setUserPassword(user, userData.getPassword());
			user.setDisplayName(userData.getDisplayName());
			user.setEmail(userData.getEmail());
			user = setUserRoles(user, userData.getRolesDesc());
			user.setDepartment(userData.getDepartment());
			user.setInfo(userData.getInfo());		
			userService.updateUser(user);
			LOG.info("User " + user.getUsername() + " changed by " + currentUser.getUsername());
			return "redirect:admin.do";
		}
		LOG.warn("Only administrators can change users data!");
		LOG.info(currentUser.getUsername() + " -Access DENIED-");
		return "redirect: /index.do";
	}
	
	private User setUserPassword(User user, String password) {
		if (!password.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			password = encoder.encode(password);
			user.setPassword(password);
		}
		return user;
	}
	
	private User setUserRoles(User user, String rolesDesc) {
		user.setRoles(null);
		if (rolesDesc.contains("ROLE_ADMIN")) {
			user.addRoleAdmin();
		}
		if (rolesDesc.contains("ROLE_TEACHER")) {
			user.addRoleTeacher();
		}
		if (rolesDesc.contains("ROLE_USER")) {
			user.addRoleUser();
		}
		if (user.getRoles() == null) {
			user.addRoleUser();
		}
		return user;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("username") String username, Principal principal) {
		UserDetails currentUser = 
				(UserDetails) ((Authentication) principal).getPrincipal();
		LOG.info(currentUser.getUsername() + " try to delete user " + username);
		GrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
		if (currentUser.getAuthorities().contains(admin)) {
			LOG.info(currentUser.getUsername() + " -Access GRANTED-");
			LOG.info("User can be deleted");
			if (!principal.getName().equals(username)) {
				userService.deleteUserByName(username);
				LOG.info("User " + username + " deleted by " + currentUser.getUsername());
			} else {
				LOG.warn("User cann't delete themself!");
			}
			return "redirect:admin.do";
		}
		LOG.warn("Only administrators can change users data!");
		LOG.info(currentUser.getUsername() + " -Access DENIED-");
		return "redirect: /index.do";
	}
}
