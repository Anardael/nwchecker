package com.nwchecker.server.dao;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;

import java.util.List;

/**
 * <h1>User DAO</h1> DAO for working with User Entity table.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @author Stanislav Krasovskyi
 * @version 1.0
 */
public interface UserDAO {

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
	 * Delete User from database.
	 * <p>
	 *
	 * @param user
	 *            User that will be removed from DB
	 */
	public void deleteUser(User user);

	/**
	 * Delete specific role of some User.
	 * <p>
	 *
	 * @param role
	 *            Role that will be removed
	 */
	public void deleteRole(Role role);

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
     * @param username Username of existing User
     * @return User from DB
     */
	public User getUserByUsername(String username);

    /**
     * Return User by specific Email
     * <p>
     *
     * @param email Email of existing User
     * @return User from DB
     */
    public User getUserByEmail(String email);

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

	/**
	 * Return Users that have at least one request.
	 * <p>
	 *
	 * @return List of Users that have requests
	 */
	public List<User> getUsersWithRequests();
	/**
	 * Returns list of Users from database.
	 * @param startIndex
	 *            Start index of records for this page.
	 * @param pageSize
	 *            Size of the page.
	 * @param sortingColumn
	 *            A string represents requested sorting column name.
	 * @param sortingOrder
	 *            A string that represents order in which the sorting is made
	 *            (asc or desc)
	 * @param filter
	 *            String that represents search/filter for User.
	 * @return List of Users.
	 */
	
	public List<User> getPagedUsers(int startIndex, int pageSize, String sortingColumn, String sortingOrder, String filter);
	/**
	 * Returns list of Users from database.
	 * @param startIndex
	 *            Start index of records for this page.
	 * @param pageSize
	 *            Size of the page.
	 * @return List of Users.
	 */
	public List<User> getPagedUsers(int startIndex, int pageSize);
	/**
	 * Return count of Users that fit the filter criteria.
	 * @param filter
	 *            String that represents search/filter for TaskPass.
	 * @return Count of Users.
	 */
	public Long getRecordCount(String filter);
}
