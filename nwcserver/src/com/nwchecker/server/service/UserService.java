package com.nwchecker.server.service;

import com.nwchecker.server.model.User;

public interface UserService {

	public void addUser(User user);
	
	public void updateUser(User user);

	public User getUserByUsername(String username);
	
	public boolean hasUsername(String username);
	
	public boolean hasEmail(String email);

}
