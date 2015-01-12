package com.nwchecker.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

@Controller
@SessionAttributes("user")
public class ProfileController {

	private final UserService	userService;

	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier("userValidator")
	private Validator	validator;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setValidator(validator);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String initRegistrationForm(ModelMap model, Principal principal) {
		String username = principal.getName(); // get logged in username
		User user = userService.getUserByUsername(username);
		model.addAttribute("userProfile", user);
		return "/profile";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("userProfile") @Validated User user, BindingResult result) {
		if (result.hasErrors()) {
			return "/profile";
		} else {
			userService.updateUser(user);
			return "/userCreated";
		}
	}
}
