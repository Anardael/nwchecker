package com.nwchecker.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;

import org.apache.log4j.Logger;
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

	private static final Logger LOG = Logger.getLogger(AdminOptionsController.class);
	
	@Autowired
    private UserService userService;

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
    public String userRequests(Principal principal) {
    	LOG.info("\"" + principal.getName() + "\" tries to access administrator page(users requests).");
		LOG.info("\"" + principal.getName() + "\" have access to administrator page(users requests).");
    	return "adminOptions/userRequests";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getUsersWithRequests", method = RequestMethod.GET)
    public @ResponseBody String getUsersWithRequests(Principal principal) {
    	LOG.info("\"" + principal.getName() + "\" tries to access users requests list.");
    	List<User> users = userService.getUsersWithRequests();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsonData = gson.toJson(users.toArray());
        LOG.info("\"" + principal.getName() + "\" received users requests list.");
        return jsonData;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/acceptUserRequests", method = RequestMethod.POST)
    public String acceptUserRequests() {
    	LOG.info("Accessed \"/acceptUserRequests.do\"");
        return "adminOptions/userRequests";
    }
}
