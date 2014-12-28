package com.nwchecker.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
}
