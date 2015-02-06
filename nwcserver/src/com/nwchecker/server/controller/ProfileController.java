package com.nwchecker.server.controller;

import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.service.UserService;
import com.nwchecker.server.validators.UserProfileValidator;

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
		return "profileOptions/profile";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String doUpdateProfile(@ModelAttribute("userProfile") @Validated User user, BindingResult result,
								  Principal principal, Model model) {
		String username = principal.getName(); // get logged in username
		User logedUser = userService.getUserByUsername(username);
		user.setRoles(logedUser.getRoles());
		user.setRequests(logedUser.getRequests());
		if (result.hasErrors()) {
			return "profileOptions/profile";
		} else {
			logedUser.setDisplayName(user.getDisplayName());
			logedUser.setDepartment(user.getDepartment());
			logedUser.setInfo(user.getInfo());
			userService.updateUser(logedUser);
			model.addAttribute("userUpdated", "true");
			return "profileOptions/profile";
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String doChangePassword(HttpServletRequest request, Principal principal, ModelMap model) {
		String patternPassword = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,32})";
		String username = principal.getName(); // get logged in username
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User logedUser = userService.getUserByUsername(username);
		String oldPassword = (String) request.getParameter("oldPassword");
		model.addAttribute("userProfile", logedUser);
		if (encoder.matches(oldPassword, logedUser.getPassword())) {
			String newPassword = (String) request.getParameter("newPassword");
			String confirmPassword = (String) request.getParameter("confirmPassword");
			if (newPassword.equals(confirmPassword)) {
				if (newPassword.matches(patternPassword)) {
					logedUser.setPassword(encoder.encode(newPassword));
					userService.updateUser(logedUser);
					model.addAttribute("passwordChanged", "true");
				} else {
					model.addAttribute("passwordChanged", "false");
				}
			} else {
				model.addAttribute("passwordChanged","false");
			}
		} else {
			model.addAttribute("passwordChanged", "false");
		}
		return "profileOptions/profile";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addUserRequest", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse addUserRequest(@RequestParam(value = "request") String request,  Principal principal) {
		String username = principal.getName(); // get logged in username
		User user = userService.getUserByUsername(username);
		UserRequest newUserRequest = new UserRequest(user,request);
		user.getRequests().add(newUserRequest);
		userService.updateUser(user);
		ValidationResponse result = new ValidationResponse();
		result.setStatus("Success");
		return result;
	}

}
