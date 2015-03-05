package com.nwchecker.server.validators;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {

	private final String	patternUsername		= "^[a-zA-Z]{1}[a-zA-Z0-9_-]{2,15}$";
	private final String	patternDisplayName	= "^[а-яіїєА-ЯІЇЄa-zA-Z]{1}[а-яіїєА-ЯІЇЄa-zA-Z0-9_-]{2,15}$";
	private final String	patternEmail		= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
														+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String	patternPassword		= "^(?=.{6,32}$)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?!.*[А-я]).*";
	
	private final UserService userService;
	
	public UserRegistrationValidator() {
		this.userService = null;
	}
	
	@Autowired
	public UserRegistrationValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "reg.empty.username.caption");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "reg.empty.displayName.caption");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "reg.empty.email.caption");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "reg.empty.password.caption");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "reg.empty.confirmPassword.caption");

		User user = (User) obj;
		boolean hasUsername = userService.hasUsername(user.getUsername());
		boolean hasEmail = userService.hasEmail(user.getEmail());
		
		if (!user.getUsername().matches(patternUsername)) {
			errors.rejectValue("username", "reg.badUsername.caption");
		}
		if (!user.getDisplayName().matches(patternDisplayName)) {
			errors.rejectValue("displayName", "reg.badDisplayName.caption");
		}
		if (!user.getEmail().matches(patternEmail)) {
			errors.rejectValue("email", "reg.badEmail.caption");
		}
		if (!user.getPassword().matches(patternPassword)) {
			errors.rejectValue("password", "reg.badPassword.caption");
		}
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword", "reg.badConfirmPassword.caption");
		}
		if (hasUsername) {
			errors.rejectValue("username", "reg.usernameNotUnique.caption");
		}
		if (hasEmail) {
			errors.rejectValue("email", "reg.emailNotUnique.caption");
		}
	}
	
	public String getPatternUsername() {
		return patternUsername;
	}
	
	public String getPatternDisplayName() {
		return patternDisplayName;
	}
	
	public String getPatternEmail() {
		return patternEmail;
	}
	
	public String getPatternPassword() {
		return patternPassword;
	}
}
