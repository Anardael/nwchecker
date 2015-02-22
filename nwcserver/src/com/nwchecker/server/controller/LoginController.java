package com.nwchecker.server.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger LOG = Logger
            .getLogger(AdminOptionsController.class);

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView("loggingAndRegistration/login");
        if (error != null) {
            LOG.info("Someone trying make login, but inputted not correct data.");
            model.addObject("error", "Invalid username and password!");
        }
        return model;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String initLogoutForm() {
        return "/index";
    }

}
