package com.nwchecker.server.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nwchecker.server.model.TaskPass;

@Repository("TaskPassDAO")
public class TaskPassDAOImpl extends HibernateDaoSupport implements TaskPassDAO {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	@Transactional
	@Override
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize, String sorting) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session.createQuery("FROM TaskPass t WHERE task_id = :id ORDER BY " + sorting);
		q.setParameter("id", id);
		q.setFirstResult(startIndex);
		q.setMaxResults(pageSize);
		return (List<TaskPass>) q.list();
	}

	@Transactional
	@Override
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session.createQuery("FROM TaskPass WHERE task_id = :id ORDER BY passed DESC, executionTime");
		q.setParameter("id", id);
		q.setFirstResult(startIndex);
		q.setMaxResults(pageSize);
		return (List<TaskPass>) q.list();
	}

	@Transactional
	@Override
	public Long getTaskPassResponseSize(int id) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session
				.createQuery("SELECT COUNT(*) FROM TaskPass WHERE task_id = :id");
		q.setParameter("id", id);
		Long size = (Long) q.uniqueResult();
		return size;
	}
}
