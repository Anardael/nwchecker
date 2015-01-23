package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.model.User;

public interface UserService {

	public void addUser(User user);

	public void updateUser(User user);
	
	public void deleteUserByName(String username);
	
	public void deleteUserRoles(User user);
        
    public User getUserById(int id);

	public User getUserByUsername(String username);
	
	public List<User> getUsers();
	
	public List<User> getUsersByRole(String role);

	public boolean hasUsername(String username);

	public boolean hasEmail(String email);

}
