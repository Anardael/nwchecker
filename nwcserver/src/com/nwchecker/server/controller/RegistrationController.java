package com.nwchecker.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.validators.UserValidator;

@Controller
@SessionAttributes("user")
public class RegistrationController {

	private final UserService	userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String initRegistrationForm() {
		return "/registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("userRegistrationForm") User user, BindingResult result,
			SessionStatus status, Model model, BindingResult resultModel) {
		UserValidator userValidator = new UserValidator(user);
		boolean hasUsername = userService.hasUsername(user.getUsername());
		boolean hasEmail = userService.hasEmail(user.getEmail());
		if (result.hasErrors() || hasUsername || hasEmail || !userValidator.isUserValid()) {
			//Validation username
			if (userValidator.isUsernameEmpty()) {
				model.addAttribute("usernameEmpty","true");
			} else if (!userValidator.isUsernameValid()) {
				model.addAttribute("badUsername","true");
			} else if (hasUsername) {
				model.addAttribute("usernameNotUnique","true");
			}
			//Validation display name (nickname)
			if (userValidator.isDisplayNameEmpty()) {
				model.addAttribute("displayNameEmpty","true");
			} else if (!userValidator.isDisplayNameValid()) {
				model.addAttribute("badDisplayName","true");
			}
			//Validation email	
			if (userValidator.isEmailEmpty()) {
				model.addAttribute("emailEmpty","true");
			} else if (!userValidator.isEmailValid()) {
				model.addAttribute("badEmail","true");
			} else if (hasEmail) {
				model.addAttribute("emailNotUnique","true");
			}
			//Validation password
			if (userValidator.isPasswordEmpty()) {
				model.addAttribute("passwordEmpty","true");
			} else if (!userValidator.isPasswordValid()) {
				model.addAttribute("badPassword","true");
			}
			//Validation confirm password
			if (userValidator.isConfirmPasswordEmpty()) {
				model.addAttribute("confirmPasswordEmpty","true");
			} else if (!userValidator.isConfirmPasswordValid()) {
				model.addAttribute("badConfirmPassword","true");
			}
			
			return "/registration";
		} else {
			userService.addUser(user);
			status.setComplete();
			model.addAttribute("success","true");
			return "/registration";
		}
	}
}
