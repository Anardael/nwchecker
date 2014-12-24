package com.nwchecker.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/index")
	public String main(HttpSession session,Model model) throws IllegalArgumentException{
	
		return "index";
	}
}
