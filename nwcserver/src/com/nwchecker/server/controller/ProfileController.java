package com.nwchecker.server.controller;

import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.validators.UserProfileValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@SessionAttributes("user")
public class ProfileController {

	private final UserService	userService;
    private static final Logger LOG = Logger
            .getLogger(AdminOptionsController.class);

	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private UserProfileValidator userProfileValidator;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setValidator(userProfileValidator);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = {"/profile", "/changePassword"}, method = RequestMethod.GET)
	public String initProfileForm(ModelMap model, Principal principal) {
		String username = principal.getName(); // get logged in username
		User user = userService.getUserByUsername(username);
		model.addAttribute("userProfile", user);
        LOG.info("\"" + principal.getName() + "\" initialized profile page.");
		return "profileOptions/profile";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String doUpdateProfile(@ModelAttribute("userProfile") @Validated User user, BindingResult result,
								  Principal principal, Model model) {
        LOG.info("\"" + principal.getName() + "\" trying update profile.");
		String username = principal.getName(); // get logged in username
		User loggedUser = userService.getUserByUsername(username);
		user.setRoles(loggedUser.getRoles());
		user.setRequests(loggedUser.getRequests());
		if (result.hasErrors()) {
            LOG.info("\"" + principal.getName() + "\" inputted bad data to profile.");
			return "profileOptions/profile";
		} else {
			loggedUser.setDisplayName(user.getDisplayName());
			loggedUser.setDepartment(user.getDepartment());
			loggedUser.setInfo(user.getInfo());
			userService.updateUser(loggedUser);
			model.addAttribute("userUpdated", "true");
            LOG.info("\"" + principal.getName() + "\" updated his profile.");
			return "profileOptions/profile";
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String doChangePassword(HttpServletRequest request, Principal principal, ModelMap model) {
        LOG.info("\"" + principal.getName() + "\" trying change password.");
		String patternPassword = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,32})";
		String username = principal.getName(); // get logged in username
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User loggedUser = userService.getUserByUsername(username);
		String oldPassword = request.getParameter("oldPassword");
		model.addAttribute("userProfile", loggedUser);
		if (encoder.matches(oldPassword, loggedUser.getPassword())) {
			String newPassword = request.getParameter("newPassword");
			String confirmPassword = request.getParameter("confirmPassword");
			if (newPassword.equals(confirmPassword)) {
				if (newPassword.matches(patternPassword)) {
					loggedUser.setPassword(encoder.encode(newPassword));
					userService.updateUser(loggedUser);
					model.addAttribute("passwordChanged", "true");
                    LOG.info("\"" + principal.getName() + "\" changed password.");
				} else {
                    LOG.info("\"" + principal.getName() + "\" inputted bad password.");
					model.addAttribute("passwordChanged", "false");
				}
			} else {
                LOG.info("\"" + principal.getName() + "\" inputted bad confirm password.");
				model.addAttribute("passwordChanged","false");
			}
		} else {
            LOG.info("\"" + principal.getName() + "\" inputted bad old password.");
			model.addAttribute("passwordChanged", "false");
		}
		return "profileOptions/profile";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addUserRequest", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse addUserRequest(@RequestParam(value = "request") String request,  Principal principal) {
        LOG.info("\"" + principal.getName() + "\" trying make request for role \"Teacher\".");
		String username = principal.getName(); // get logged in username
		User user = userService.getUserByUsername(username);
		UserRequest newUserRequest = new UserRequest(user,request);
		user.getRequests().add(newUserRequest);
		userService.updateUser(user);
        LOG.info("\"" + principal.getName() + "\" made request for role \"Teacher\".");
		return ValidationResponse.createValidationResponse("Success");
	}

}
