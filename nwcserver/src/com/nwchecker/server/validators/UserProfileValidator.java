package com.nwchecker.server.validators;

import com.nwchecker.server.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserProfileValidator implements Validator {

	private final String	patternDisplayName	= "^[а-яіїєА-ЯІЇЄa-zA-Z]{1}[а-яіїєА-ЯІЇЄa-zA-Z0-9_-]{2,15}$";
	private final String	patternDepartment	= "^[а-яіїєА-ЯІЇЄa-zA-Z0-9'\" -]*$";
	private final String	patternInfo			= "^[а-яіїєА-ЯІЇЄa-zA-Z0-9:.;,!?'\")( -]*$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "profile.empty.displayName.caption");

		User user = (User) obj;
		
		if (!user.getDisplayName().matches(patternDisplayName)) {
			errors.rejectValue("displayName", "profile.badDisplayName.caption");
		}
		
		if (!user.getDepartment().matches(patternDepartment)) {
			errors.rejectValue("department", "profile.badDepartment.caption");
		}
		
		if (!user.getInfo().matches(patternInfo)) {
			errors.rejectValue("info", "profile.badInfo.caption");
		}
	}
	
	public String getPatternDisplayName() {
		return patternDisplayName;
	}
	
	public String getPatternDepartment() {
		return patternDepartment;
	}
	
	public String getPatternInfo() {
		return patternInfo;
	}
}
