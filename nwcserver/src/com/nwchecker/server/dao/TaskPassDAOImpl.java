package com.nwchecker.server.dao;

import java.util.List;
import java.util.Map;

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
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize,
			int pageNumber, Map<String, String> orderParams) {
		String prefix = "";
		StringBuffer sb = new StringBuffer("FROM TaskPass t WHERE task_id = :id ORDER BY ");		
		for (Map.Entry<String, String> entry : orderParams.entrySet()){
			sb.append(prefix);
			prefix=",";
			sb.append(entry.getKey() + " " + entry.getValue());
		}
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session.createQuery(sb.toString());
		q.setParameter("id", id);
		q.setFirstResult((pageNumber - 1) * pageSize);
		q.setMaxResults(pageSize);
		return (List<TaskPass>) q.list();
	}

	@Transactional
	@Override
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize,
			int pageNumber) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session.createQuery("FROM TaskPass WHERE task_id = :id ORDER BY passed DESC, executionTime");
		q.setParameter("id", id);
		q.setFirstResult((pageNumber - 1) * pageSize);
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

	@Transactional
	@Override
	public List<TaskPass> getPaginatedSuccessfulTaskPassByTaskId(int id,
			int pageSize, int pageNumber) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session
				.createQuery("FROM TaskPass WHERE task_id = :id AND passed IS true");
		q.setParameter("id", id);
		q.setFirstResult((pageNumber - 1) * pageSize);
		q.setMaxResults(pageSize);
		return (List<TaskPass>) q.list();
	}

	@Transactional
	@Override
	public Long getNumberOfAttempts(int userId,int taskId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session
				.createQuery("SELECT COUNT(*) FROM TaskPass WHERE userid = :id AND task_Id = :taskId");
		q.setParameter("id", userId);
		q.setParameter("taskId", taskId);
		Long size = (Long) q.uniqueResult();
		return size;
	}

	@Transactional
	@Override
	public Long getTaskPassSuccessfulResponseSize(int id) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query q = session
				.createQuery("SELECT COUNT(*) FROM TaskPass WHERE task_id = :id AND passed IS true");
		q.setParameter("id", id);
		Long size = (Long) q.uniqueResult();
		return size;
	}

}