package com.nwchecker.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO	userDAO;

	@Override
	public void addUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setAccessLevel("ROLE_USER");
		user.setEnabled(true);
		userDAO.addUser(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	@Override
	public java.util.List<User> getUsers() {
		return userDAO.getUsers();
	};

	@Override
	public boolean hasUsername(String username) {
		return userDAO.hasUsername(username);
	}

	@Override
	public boolean hasEmail(String email) {
		return userDAO.hasEmail(email);
	}
}
