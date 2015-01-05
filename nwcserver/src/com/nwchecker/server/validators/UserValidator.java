package com.nwchecker.server.validators;

import com.nwchecker.server.model.User;

public class UserValidator {

	private final String	patternUsername		= "^[a-zA-Z0-9_-]{3,16}$";
	private final String	patternDisplayName	= "^[a-zA-Z0-9_-]{3,16}$";
	private final String	patternEmail		= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
														+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String	patternPassword		= "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,32})";

	private User			user;

	public UserValidator(User user) {
		super();
		this.user = user;
	}
	
	public boolean isUsernameEmpty() {
		return user.getUsername().isEmpty();
	}
	
	public boolean isDisplayNameEmpty() {
		return user.getDisplayName().isEmpty();
	}
	
	public boolean isEmailEmpty() {
		return user.getEmail().isEmpty();
	}
	
	public boolean isPasswordEmpty() {
		return user.getPassword().isEmpty();
	}
	
	public boolean isConfirmPasswordEmpty() {
		return user.getConfirmPassword().isEmpty();
	}

	public boolean isUsernameValid() {
		if (user.getUsername().matches(patternUsername)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDisplayNameValid() {
		if (user.getDisplayName().matches(patternDisplayName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmailValid() {
		if (user.getEmail().matches(patternEmail)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPasswordValid() {
		if (user.getPassword().matches(patternPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isConfirmPasswordValid() {
		if (user.getPassword().equals(user.getConfirmPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUserValid() {
		if (isUsernameValid() && isEmailValid() && isDisplayNameValid() && isPasswordValid()
				&& isConfirmPasswordValid()) {
			return true;
		} else {
			return false;
		}
	}
}
