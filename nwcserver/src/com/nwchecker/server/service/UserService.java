package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

public interface UserService {

	public void addUser(User user);

	public void updateUser(User user);
	
	public void deleteUserByName(String username);
	
	public void deleteUserRoles(User user);

	public void deleteRequest(User user, UserRequest userRequest);

	public void deleteUserRequests(User user);
        
    public User getUserById(int id);

	public User getUserByUsername(String username);
	
	public List<User> getUsers();
	
	public List<User> getUsersByRole(String role);

	public List<UserRequest> getUserRequests(User user);

	public List<User> getUsersWithRequests();

	public boolean hasUsername(String username);

	public boolean hasEmail(String email);

}
