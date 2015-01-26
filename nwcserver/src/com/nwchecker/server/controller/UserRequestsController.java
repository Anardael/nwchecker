package com.nwchecker.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

/**
 * Created by ReaktorDTR on 25.01.2015.
 */

@Controller
@SessionAttributes("user")
public class UserRequestsController {

    private final UserService userService;

    @Autowired
    public UserRequestsController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier("userProfileValidator")
    private Validator validator;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.setValidator(validator);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRequests", method = RequestMethod.GET)
    public String admin() {
        return "adminOptions/userRequests";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getUsersWithRequests", method = RequestMethod.GET)
    public @ResponseBody String getUsersWithRequests() {
        boolean accepted=true;
        List<User> users = userService.getUsersWithRequests();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsonData = gson.toJson(users.toArray());
        return jsonData;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/acceptUserRequests", method = RequestMethod.GET)
    public String acceptUserRequests() {
        return "adminOptions/userRequests";
    }
}
