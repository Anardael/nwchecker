/**
 * 
 */
package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.UserRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void deleteUser(User user) {
		getHibernateTemplate().delete(user);
	}
	
	@Override
	public void deleteRoles(List<Role> roles) {	
		getHibernateTemplate().deleteAll(roles);
	}

	@Override
	public void deleteRequest(User user, UserRequest userRequest) {
		user.getRequests().remove(userRequest);
		getHibernateTemplate().delete(userRequest);
	}

	@Override
	public void deleteRequests(List<UserRequest> requests) {
		getHibernateTemplate().deleteAll(requests);
	}

	@Override
	public User getUserById(int id) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from User where id=?", id);
		return (User) list.get(0);
	}

	@Override
	public User getUserByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from User where username=?", username);
		return (User) list.get(0);
	}

	@Override
	public List<User> getUsers() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from User");
		return list;
	}
	
	@Override
	public List<Role> getUserRoles(User user) {
		@SuppressWarnings("unchecked")
		List<Role> list = (List<Role>) getHibernateTemplate().find("SELECT role FROM Role role INNER JOIN role.user user WITH user.userId =?", user.getUserId());
		return list;
	}
	
	@Override
	public List<User> getUsersByRole(String role) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("SELECT user FROM User user INNER JOIN user.roles roles WHERE roles.role =?", role);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean hasUsername(String username) {
		if (((List<User>) getHibernateTemplate().find("from User user where user.username = ?", username)).size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean hasEmail(String email) {
		if (((List<User>) getHibernateTemplate().find("from User user where user.email = ?", email)).size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UserRequest> getUserRequests(User user) {
		@SuppressWarnings("unchecked")
		List<UserRequest> list = (List<UserRequest>) getHibernateTemplate().find("SELECT requests FROM UserRequest requests INNER JOIN requests.user user WITH user.userId =?", user.getUserId());
		return list;
	}

	@Override
	public List<User> getUsersWithRequests() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("SELECT user FROM User user INNER JOIN user.requests");
		return list;
	}
}
