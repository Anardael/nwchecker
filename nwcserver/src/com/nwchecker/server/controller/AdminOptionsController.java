package com.nwchecker.server.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "adminOptions/users";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public @ResponseBody String getUsers() {
		List<User> users = userService.getUsers();
		Gson gson = 
				new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(users.toArray());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/userEdit", method = RequestMethod.GET)
	public String user(HttpServletRequest request, Model model) {
		String username = request.getParameter("Username");
		if (username == null) {
			return "redirect:admin.do";
		}
		User user = userService.getUserByUsername(username);
		user.setPassword("");
		model.addAttribute("userData", user);
		return "adminOptions/userEdit";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	public String changeUser(@ModelAttribute("userData") @Validated User userData, BindingResult result) {
		if (result.hasErrors()) {
			return "/adminOptions/userEdit";
		}
		User user = userService.getUserByUsername(userData.getUsername());
		user.setPassword(getPasswordHash(userData.getPassword()));
		user.setDisplayName(userData.getDisplayName());
		user.setEmail(userData.getEmail());
		user.setDepartment(userData.getDepartment());
		user.setInfo(userData.getInfo());
		userService.updateUser(user);
		return "redirect:admin.do";
	}
	
	private String getPasswordHash(String password) {
		if (!password.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			password = encoder.encode(password);
		}
		return password;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("username") String username, Principal principal) {
		if (!principal.getName().equals(username)) {
			userService.deleteUserByName(username);
		}
		return "redirect:admin.do";
	}
}
