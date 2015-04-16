package com.nwchecker.server.validators;

import com.nwchecker.server.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * <h1>User Edit Description Validator</h1>
 * Validator that used to validate user data from User Edit page.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 */
@Component
public class UserEditValidator implements Validator {

	private final String	patternPassword		= "^(?=.{6,32}$)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?!.*[А-я]).*";
	private final String	patternDisplayName	= "^[а-яіїєА-ЯІЇЄa-zA-Z]{1}[а-яіїєА-ЯІЇЄa-zA-Z0-9_-]{2,15}$";
	private final String	patternEmail		= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
												+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String	patternDepartment	= "^[а-яіїєА-ЯІЇЄa-zA-Z0-9'\" -]*$";
	private final String	patternInfo			= "^[а-яіїєА-ЯІЇЄa-zA-Z0-9:.;,!?'\")( -]*$";
	private final String	patternPhone		= "[0-9)(+]{10}";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "adminPanel.userEdit.displayName.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "adminPanel.userEdit.email.empty.error");
		
		User user = (User) obj;
		
		if (!user.getPassword().isEmpty() && !user.getPassword().matches(patternPassword)) {
			errors.rejectValue("password", "adminPanel.userEdit.password.error");
		}
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword", "adminPanel.userEdit.confirmPass.error");
		}
		if (!user.getDisplayName().matches(patternDisplayName)) {
			errors.rejectValue("displayName", "adminPanel.userEdit.displayName.error");
		}
		if (!user.getEmail().matches(patternEmail)) {
			errors.rejectValue("email", "adminPanel.userEdit.email.error");
		}
		if (!user.getDepartment().matches(patternDepartment)) {
			errors.rejectValue("department", "adminPanel.userEdit.department.error");
		}
		if (!user.getInfo().matches(patternInfo)) {
			errors.rejectValue("info", "adminPanel.userEdit.info.error");
		}
		if (!user.getPhone().matches(patternPhone)) {
			errors.rejectValue("phone", "adminPanel.userEdit.phone.error");
		}
	}
	
	public String getPatternPassword() {
		return patternPassword;
	}
	
	public String getPatternDisplayName() {
		return patternDisplayName;
	}
	
	public String getPatternEmail() {
		return patternEmail;
	}
	
	public String getPatternDepartment() {
		return patternDepartment;
	}
	
	public String getPatternInfo() {
		return patternInfo;
	}
	
	public String getPatternPhone() {
		return patternPhone;
	}
}
