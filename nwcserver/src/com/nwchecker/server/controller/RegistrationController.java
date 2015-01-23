package com.nwchecker.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

@Controller
@SessionAttributes("user")
public class RegistrationController {

	private final UserService	userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier("userRegistrationValidator")
	private Validator	validator;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setValidator(validator);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String initRegistrationForm(Model model) {
		model.addAttribute("userRegistrationForm", new User());
		return "/registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("userRegistrationForm") @Validated User user, BindingResult result) {
		if (result.hasErrors()) {
			return "/registration";
		} else {
			userService.addUser(user);
			return "/userCreated";
		}
	}
}
