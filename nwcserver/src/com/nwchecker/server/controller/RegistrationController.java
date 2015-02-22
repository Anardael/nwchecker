package com.nwchecker.server.controller;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class RegistrationController {

    private final UserService userService;
    private static final Logger LOG = Logger
            .getLogger(AdminOptionsController.class);

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier("userRegistrationValidator")
    private Validator validator;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.setValidator(validator);
    }

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String initRegistrationForm(Model model) {
        LOG.info("Someone initialized registration page");
        model.addAttribute("userRegistrationForm", new User());
        return "loggingAndRegistration/registration";
    }

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("userRegistrationForm") @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            LOG.info("Someone trying make registration, but inputted not correct data.");
            return "loggingAndRegistration/registration";
        } else {
            userService.addUser(user);
            LOG.info("\"" + user.getUsername() + "\" is registered .");
            return "loggingAndRegistration/userCreated";
        }
    }
}
