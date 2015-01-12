package com.nwchecker.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminOptionsController {

	@Autowired
	private UserService userService;
	
	// "/admin.do"
	@RequestMapping(method = RequestMethod.GET)
	public String adminOption(HttpServletRequest request) {
		String option = request.getParameter("option");
		if (option == null) {
			return "adminOptions/default";
		}
		switch (option) {
			case "users":
				return "adminOptions/users";
			case "userEdit":
				return "adminOptions/userEdit";
			default:
				return "adminOptions/default";
		}
	}
	
	// "/admin/getUsers.do" - return JSON data
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers() {
		return userService.getUsers();
	}
}
