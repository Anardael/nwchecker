package com.nwchecker.server.controller;

import java.util.Map;

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

@Controller
@SessionAttributes("user")
public class UserController {

	private final UserService	userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String initRegistrationForm(Map<String, Object> model) {
		User user = new User();
		model.put("userRegistrationForm", user);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("userRegistrationForm") User user, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "/registration";
		} else {
			user.setEnabled(true);
			user.setAccessLevel("User");
			this.userService.addUser(user);
			status.setComplete();
			return "redirect:/index/" + user.getId();
		}
	}
}
