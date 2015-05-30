package com.nwchecker.server.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	public List<TaskPass> getPaginatedTaskPassByTaskIdSorted(int id,
			int startIndex, int pageSize, String sorting) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		if (sorting.contains("Username")) {
			sorting = sorting.replace("Username", "t.user.displayName");
		}
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
			int pageSize, String sortingColumn, String sortingOrder,
			String filter) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Criteria criteria = session.createCriteria(TaskPass.class);
		criteria.createAlias("user", "u");
		if (StringUtils.isNotEmpty(sortingColumn)) {
			if (StringUtils.equals(sortingColumn, "Username")) {
				sortingColumn = "u.displayName";
			}
			if (sortingOrder.equalsIgnoreCase("ASC")) {
				criteria.addOrder(Order.asc(sortingColumn));
			} else {
				criteria.addOrder(Order.desc(sortingColumn));
			}
		}
		if (StringUtils.isNotBlank(filter)) {
			criteria.add(Restrictions.ilike("u.displayName", filter,
					MatchMode.ANYWHERE));
		}
		criteria.createCriteria("task", "t");
		criteria.add(Restrictions.eq("t.id", id));
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		return criteria.list();
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
	public Long getTaskPassEntryCount(int id, String filter) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Criteria criteria = session.createCriteria(TaskPass.class);
		criteria.createAlias("user", "u");
		if (StringUtils.isNotBlank(filter)) {
			criteria.add(Restrictions.ilike("u.displayName", filter,
					MatchMode.ANYWHERE));
		}
		criteria.createCriteria("task", "t");
		criteria.add(Restrictions.eq("t.id", id));
		criteria.setProjection(Projections.rowCount());
		Long size = (Long) criteria.uniqueResult();
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
