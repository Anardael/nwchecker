package com.nwchecker.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskCreationController {

	@RequestMapping("/taskCreation")
	public String wysiwyg(HttpSession session,Model model) throws IllegalArgumentException{
		
		return "taskCreation";
	}
}
