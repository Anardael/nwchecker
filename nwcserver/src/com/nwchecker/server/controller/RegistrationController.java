package com.nwchecker.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @RequestMapping("/registration")
    public String getPage(Model model) {
        return "reg";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }
}
