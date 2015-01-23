package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;

public interface UserDAO {

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public void deleteRoles(List<Role> roles);
	
	public User getUserById(int id);

	public User getUserByUsername(String username);

	public List<User> getUsers();
	
	public List<Role> getUserRoles(User user);
	
	public List<User> getUsersByRole(String role);
	
	public boolean hasUsername(String username);
	
	public boolean hasEmail(String email);

}
