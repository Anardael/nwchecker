package com.nwchecker.server.controller;

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

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

/**
 * <h1>Registration Controller</h1>
 * This spring controller contains mapped methods, that
 * allows to register new user in system.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */
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

    /**
     * This mapped method used to return page where user
     * can pass registration process.
     * <p>
     *
     * @param model Spring Framework model for this page
     * @return <b>registration.jsp</b> Returns page where user can register
     */
    @Link(label="registration.caption", family="profile", parent = "")
    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String initRegistrationForm(Model model) {
        LOG.info("Someone initialized registration page");
        model.addAttribute("userRegistrationForm", new User());
        model.addAttribute("pageName", "registration");
        return "nwcserver.user.registration";
    }

    /**
     * This mapped method used to register new user in system.
     * <p>
     *
     * @param user Data set that contains information about user
     * @param result General spring interface that used in data validation
     * @return Redirects to success page or shows validation errors
     */
    @PreAuthorize("!isAuthenticated()")
    @Link(label="registration.caption", family="profile", parent = "")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("userRegistrationForm") @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOG.info("Someone trying make registration, but inputted not correct data.");
            model.addAttribute("pageName", "userCreated");
            return "nwcserver.user.registration";
        } else {
            userService.addUser(user);
            LOG.info("\"" + user.getUsername() + "\" is registered .");
            model.addAttribute("pageName", "userCreated");
            return "nwcserver.user.created";
        }
    }
}
