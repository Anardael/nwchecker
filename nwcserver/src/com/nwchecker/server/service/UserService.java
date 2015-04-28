package com.nwchecker.server.service;

import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

import java.util.List;

/**
 * <h1>User Service</h1> Service that works with Users.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @author Stanislav Krasovskyi
 * @version 1.0
 */
public interface UserService {

	/**
	 * Add new User to database.
	 * <p>
	 *
	 * @param user
	 *            User that will be inserted in DB
	 */
	public void addUser(User user);

	/**
	 * Update existing User in database.
	 * <p>
	 *
	 * @param user
	 *            User that will be updated in DB
	 */
	public void updateUser(User user);

	/**
	 * Delete existing User by his Username.
	 * <p>
	 *
	 * @param username
	 *            Unique Username of existing User
	 */
	public void deleteUserByName(String username);

	/**
	 * Delete specific role of some User.
	 * <p>
	 *
	 * @param role
	 *            Role that will be removed
	 */
	public void deleteUserRole(User user, String role);

	/**
	 * Delete specific Request of some User.
	 * <p>
	 *
	 * @param user
	 *            User whose role will be removed
	 * @param userRequest
	 *            Request that will be removed
	 */
	public void deleteRequest(User user, UserRequest userRequest);

	/**
	 * Return some User by unique ID.
	 * <p>
	 *
	 * @param id
	 *            Unique ID of existing User
	 * @return User from DB
	 */
	public User getUserById(int id);

	/**
	 * Return User by specific Username
	 * <p>
	 *
	 * @param username
	 *            Username of existing User
	 * @return User from DB
	 */
	public User getUserByUsername(String username);

	/**
	 * Return all Users from database.
	 * <p>
	 *
	 * @return List of Users
	 */
	public List<User> getUsers();

	/**
	 * Return all Users that have specific Role.
	 * <p>
	 *
	 * @param role
	 *            Specific Role of required users
	 * @return List of Users that have required Role
	 */
	public List<User> getUsersByRole(String role);

	/**
	 * Return Users that have at least one request.
	 * <p>
	 *
	 * @return List of Users that have requests
	 */
	public List<User> getUsersWithRequests();

	/**
	 * Checking if some Username exists.
	 * <p>
	 *
	 * @param username
	 *            Username for checking
	 * @return <b>true</b> if Username exists <b>false</b> if Username do not
	 *         exists
	 */
	public boolean hasUsername(String username);

	/**
	 * Checking if some Email exists.
	 * <p>
	 *
	 * @param email
	 *            Email for checking
	 * @return <b>true</b> if Email exists <b>false</b> if Email do not exists
	 */
	public boolean hasEmail(String email);

	public User getUserByUsernameOrEmail(String username);
}
