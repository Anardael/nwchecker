package com.nwchecker.server.dao;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;
import com.nwchecker.server.model.UserRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public void deleteRole(Role role) {	
		getHibernateTemplate().delete(role);
	}

	@Override
	public void deleteRequest(User user, UserRequest userRequest) {
		user.getRequests().remove(userRequest);
		getHibernateTemplate().delete(userRequest);
	}

	@Override
	public User getUserById(int id) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from User where id=?", id);
		return list.get(0);
	}

	@Override
	public User getUserByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from User where username=?", username);
		return list.get(0);
	}

	@Override
	public List<User> getUsers() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from User");
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
		return !getHibernateTemplate().find("from User user where user.username = ?", username).isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean hasEmail(String email) {
		return !getHibernateTemplate().find("from User user where user.email = ?", email).isEmpty();
	}

	@Override
	public List<User> getUsersWithRequests() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("SELECT user FROM User user INNER JOIN user.requests");
		return list;
	}
}
