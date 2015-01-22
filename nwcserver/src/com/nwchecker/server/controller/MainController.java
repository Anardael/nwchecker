package com.nwchecker.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping("/index.do")
    public String main(HttpSession session, Model model) throws IllegalArgumentException {
        return "index";
    }

    @RequestMapping({"/inDevelopment", "/donec", "/vestibulum", "/etiam", "/phasellus", "/news", "/rating"})
    public String willBeCreated(HttpSession session, Model model) throws IllegalArgumentException {
        return "/inDevelopment";
    }
}
