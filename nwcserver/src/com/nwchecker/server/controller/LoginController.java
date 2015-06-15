package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.listener.HttpSessionListenerImpl;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * <h1>Login Controller</h1>
 * This spring controller contains mapped methods, that
 * allows any user to login and logout in system.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */
@Controller
public class LoginController {

    private static final Logger LOG = Logger
            .getLogger(AdminOptionsController.class);

    @Autowired
    UserService userService;

    /**
     * This mapped method used to login user in system.
     * <p>
     *
     * @param error if process "login" have some errors, value will change to "not null".
     */
    @Link(label="login.caption", family="User", parent = "")
    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView("nwcserver.user.login");
        model.addObject("pageName", "login");
        if (error != null) {
            LOG.info("Someone trying make login, but inputted not correct data.");
            model.addObject("error", "Invalid username and password!");
        }
        return model;
    }

    /**
     * This mapped method used to logout user from system.
     * <p>
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String initLogoutForm(Model model) {
    	model.addAttribute("pageName", "home");
        return "nwcserver.static.index";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginFacebookUser(Principal principal, HttpServletRequest request){
        User user = userService.getUserByUsername(principal.getName());
        HttpSession session = request.getSession();

        session.setAttribute("nickname", user.getDisplayName());
        if(user.hasRole("ROLE_TEACHER")){
            HttpSessionListenerImpl.addSession(session.getId(), session);
        }
        return "nwcserver.static.index";
    }
}
