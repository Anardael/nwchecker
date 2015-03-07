package com.nwchecker.server.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <h1>Roles Description Validator</h1>
 * Validator that used to validate roles data from User Edit page.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 */
@Component
public class RolesDescriptionValidator implements Validator {

	private final String patternRolesDescription = "^(ROLE_ADMIN;)?(ROLE_TEACHER;)?(ROLE_USER;)?$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return String.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		String rolesDesc = (String) obj;
		
		if ((rolesDesc == null) || (rolesDesc.isEmpty())
			|| (!rolesDesc.matches(patternRolesDescription))) {
			errors.rejectValue("roles", "adminPanel.userEdit.roles.error");
		}
	}
	
	public String getRolesDescPattern() {
		return patternRolesDescription;
	}
}
