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

		Query query = session
				.createQuery("FROM TaskPass t WHERE task_id = :id ORDER BY "
						+ sorting);
		query.setParameter("id", id);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return (List<TaskPass>) query.list();
	}

	@Transactional
	@Override
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery("FROM TaskPass WHERE task_id = :id");
		query.setParameter("id", id);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return (List<TaskPass>) query.list();
	}

	@Transactional
	@Override
	public Long getTaskPassEntryCount(int id) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session
				.createQuery("SELECT COUNT(*) FROM TaskPass WHERE task_id = :id");
		query.setParameter("id", id);
		Long size = (Long) query.uniqueResult();
		return size;
	}

	@Transactional
	@Override
	public Long getSuccessfulTaskPassEntryCount(int id) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session
				.createQuery("SELECT COUNT(*) FROM TaskPass WHERE task_id = :id AND passed = :passed");
		query.setParameter("id", id);
		query.setParameter("passed", true);
		Long size = (Long) query.uniqueResult();
		return size;
	}
}
