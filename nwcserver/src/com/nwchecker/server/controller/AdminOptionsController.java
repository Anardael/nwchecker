package com.nwchecker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminOptionsController {

	@Autowired
	private UserService userService;
	
	// "/admin.do"
	@RequestMapping(method = RequestMethod.GET)
	public String admin() {
		return "adminOptions/users";
	}
	
	// "/admin/getUsers.do" - return JSON data
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public @ResponseBody String getUsers() {
		List<User> users = userService.getUsers();
		Gson gson = 
				new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(users.toArray());
	}
	
	// "/admin/user.do?Username=..." - return user in Model
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(@RequestParam("Username") String username) {
		ModelAndView modelAndView = new ModelAndView("adminOptions/userEdit");
		User user = userService.getUserByUsername(username);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
}
