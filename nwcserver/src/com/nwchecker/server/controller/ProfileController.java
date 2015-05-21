package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
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

/**
 * <h1>Profile Controller</h1>
 * This spring controller contains mapped methods, that
 * allows user to edit his profile data.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */
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

    /**
     * This mapped method used to return page where user
     * can change his profile data.
     * <p>
     * <b>Note:</b>user need to be authenticated.
     *
     * @param model Spring Framework model for this page
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return <b>profile.jsp</b> Returns page where user can change his profile data
     */
	@Link(label="profile.caption", family="profile", parent = "")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = {"/profile", "/changePassword"}, method = RequestMethod.GET)
	public String initProfileForm(ModelMap model, Principal principal) {
		String username = principal.getName(); // get logged in username
		User user = userService.getUserByUsername(username);
		model.addAttribute("userProfile", user);
        LOG.info("\"" + principal.getName() + "\" initialized profile page.");
        model.addAttribute("pageName", "login");
		return "nwcserver.user.profile";
	}

    /**
     * This mapped method used to receive changed profile data
     * and update user in database.
     * <p>
     * <b>Note:</b>user needs to be authenticated.
     *
     * @param user Data set that contains new information about user
     * @param result General spring interface that used in data validation
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param model Spring Framework model for this page
     */
	@Link(label="profile.caption", family="profile", parent = "")
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
			return "nwcserver.user.profile";
		} else {
			loggedUser.setDisplayName(user.getDisplayName());
			loggedUser.setDepartment(user.getDepartment());
			loggedUser.setInfo(user.getInfo());
			userService.updateUser(loggedUser);
			model.addAttribute("userUpdated", "true");
            LOG.info("\"" + principal.getName() + "\" updated his profile.");
			return "nwcserver.user.profile";
		}
	}

    /**
     * This mapped method used to receive new user password
     * and update user in database.
     * <p>
     * <b>Note:</b>user needs to be authenticated.
     *
     * @param request HttpServletRequest object
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @param model Spring Framework model for this page
     */
	@Link(label="profile.caption", family="profile", parent = "")
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
		return "nwcserver.user.profile";
	}

    /**
     * This mapped method used to add new user request in database.
     * <p>
     * <b>Note:</b>user needs to be authenticated.
     *
     * @param request HttpServletRequest object
     * @param principal This is general information about user, who
     *                  tries to call this method
     * @return Status "SUCCESS" or "FAIL"
     */
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
