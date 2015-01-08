package com.nwchecker.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

@Controller
public class AdminOptionsController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminOption(Model model, HttpServletRequest request) {
		String option = request.getParameter("option");
		if (option == null) {
			return "adminOptions/default";
		}
		switch (option) {
			case "users":
				List<User> users = userService.getUsers();
				model.addAttribute("users", users);
				return "adminOptions/users";
			default:
				return "adminOptions/default";
		}
	}
}
