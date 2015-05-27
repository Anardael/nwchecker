package com.nwchecker.server.service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import com.nwchecker.server.utils.PaginationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void addUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.addRole("ROLE_USER");
		userDAO.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUserByName(String username) {
		User user = userDAO.getUserByUsername(username);
		userDAO.deleteUser(user);
	}

	@Override
	public void deleteUserRole(User user, String role) {
		Role roleObject = user.getRoleObject(role);
		user.getRoles().remove(roleObject);
		userDAO.deleteRole(roleObject);
	}

	@Override
	public void deleteRequest(User user, UserRequest userRequest) {
		userDAO.deleteRequest(user, userRequest);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public List<User> getUsersByRole(String role) {
		return userDAO.getUsersByRole(role);
	}

	@Override
	public boolean hasUsername(String username) {
		return userDAO.hasUsername(username);
	}

	@Override
	public boolean hasEmail(String email) {
		return userDAO.hasEmail(email);
	}

	@Override
	public List<User> getUsersWithRequests() {
		return userDAO.getUsersWithRequests();
	}

	@Override
	public List<User> getPagedUsers(int startIndex, int pageSize,
			String sortingColumn, String sortingOrder, String filter) {
		List<User> pagedUsers = userDAO.getPagedUsers(startIndex, pageSize,
				sortingColumn, sortingOrder, filter);
		return pagedUsers;
	}

	@Override
	public Long getRecordCount(String filter) {
		return userDAO.getRecordCount(filter);
	}

	@Override
	public PaginationWrapper<User> getUsersForPagination(int startIndex,
			int pageSize, String sortingColumn, String sortingOrder,
			String filter) {
		PaginationWrapper<User> response = new PaginationWrapper<User>();
		List<User> userList = getPagedUsers(startIndex, pageSize,
				sortingColumn, sortingOrder, filter);
		response.setDataList(userList);
		response.setRecordCount(getRecordCount(filter));
		return response;
	}
}
