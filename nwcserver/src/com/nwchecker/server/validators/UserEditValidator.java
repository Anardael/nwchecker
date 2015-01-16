package com.nwchecker.server.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nwchecker.server.model.User;

public class UserEditValidator implements Validator {

	private final String	patternPassword		= "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,32})";
	private final String	patternDisplayName	= "^[a-zA-Z]+[a-zA-Z0-9_-]{2,15}$";
	private final String	patternEmail		= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
												+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String	patternDepartment	= "^[a-zA-Z0-9_ -]*$";
	private final String	patternInfo	= "^[a-zA-Z0-9_ -]*$";
	
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
	}
}
