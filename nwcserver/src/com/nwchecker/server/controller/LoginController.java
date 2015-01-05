package com.nwchecker.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nwchecker.server.service.UserService;

@Controller
public class LoginController {
	
	private final UserService	userService;

	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(value = "/login")
	public String initLoginForm() {
		return "/login";
	}
	
	@RequestMapping(value = "/logout")
	public String initLogoutForm() {
		return "/index";
	}
	
}
