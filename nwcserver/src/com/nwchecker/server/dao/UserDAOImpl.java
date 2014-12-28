/**
 * 
 */
package com.nwchecker.server.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public User getUserById(int id) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from USERS where ID=?", id);

		return (User) list.get(0);
	}

	@Override
	public User getUserByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from USERS where USERNAME=?", username);
		return (User) list.get(0);
	}

	@Override
	public List<User> getUsers() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find("from USERS");
		return list;
	}
}
