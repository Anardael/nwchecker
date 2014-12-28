package com.nwchecker.server.service;

import com.nwchecker.server.model.User;

public interface UserService {

	public void addUser(User user);

	public User getUserByUsername(String username);

}
