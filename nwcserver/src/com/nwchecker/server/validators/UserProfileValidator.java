package com.nwchecker.server.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nwchecker.server.model.User;

@Component
public class UserProfileValidator implements Validator {

	private final String	patternDisplayName	= "^[а-яА-Яa-zA-Z]{1}[а-яА-Яa-zA-Z0-9_-]{1,14}$";
	private final String	patternDepartment	= "^[а-яА-Яa-zA-Z0-9'\" -]*$";
	private final String	patternInfo			= "^[а-яА-Яa-zA-Z0-9:.;,!?'\")( -]*$";
	
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
