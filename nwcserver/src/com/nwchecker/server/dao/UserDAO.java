package com.nwchecker.server.dao;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

import java.util.List;

public interface UserDAO {

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public void deleteRole(Role role);

	public void deleteRequest(User user, UserRequest userRequest);

	public User getUserById(int id);

	public User getUserByUsername(String username);

	public List<User> getUsers();
	
	public List<User> getUsersByRole(String role);
	
	public boolean hasUsername(String username);
	
	public boolean hasEmail(String email);

	public List<User> getUsersWithRequests();

}
