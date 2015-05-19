package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("TaskDAO")
public class TaskDAOImpl extends HibernateDaoSupport implements TaskDAO {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public Task getTaskById(int id) {
		return getHibernateTemplate().get(Task.class, id);
	}

	@Override
	public List<Task> getTasksByContestId(int id) {
		@SuppressWarnings("unchecked")
		List<Task> t = (List<Task>) getHibernateTemplate().find(
				"From Task where contest_id=?", id);
		return t;
	}

	@Override
	public List<Task> getTasks() {
		@SuppressWarnings("unchecked")
		List<Task> result = (List<Task>) getHibernateTemplate().find(
				"from Task");
		return result;
	}

	@Override
	public void addTask(Task t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public void updateTask(Task t) {
		getHibernateTemplate().merge(t);
	}

	@Override
	public void deleteTaskById(int id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(Task.class, id));
	}

	@Override
	public TaskData getTaskData(int id) {
		return getHibernateTemplate().get(TaskData.class, id);
	}

	@Override
	public void deleteTaskData(int id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(TaskData.class, id));
	}

	@Override
	public List<Task> getTasksByContestStatus(Status status) {
		List<Task> result = (List<Task>) getHibernateTemplate().find("from Task t where t.contest.status = ?", status);
		return result;
	}

	@Transactional
	@Override
	public List<Task> getPagedTasksByContestStatus(Contest.Status status,
			int pageSize, int startIndex, String filter) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Criteria criteria = session.createCriteria(Task.class);
		System.out.println(filter);
		filter = "%"+filter+"%";
		System.out.println(filter);
		Criterion title = Restrictions.ilike("title", filter, MatchMode.ANYWHERE);
		Criterion description = Restrictions.ilike("description", filter, MatchMode.ANYWHERE);
		criteria.add(Restrictions.or(title, description));
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List<Task> result = criteria.list();
		for (Task t : result){
			System.out.println(t.getId());
		}
		return result;
	}
	@Transactional
	@Override
	public List<Task> getPagedTasksByContestStatus(Contest.Status status,
			int pageSize, int startIndex) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Criteria criteria = session.createCriteria(Task.class);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List<Task> result = criteria.list();
		return result;
	}

	@Transactional
	@Override
	public Long getRecordCountByContestStatus(Status status, String filter) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Criteria criteria = session.createCriteria(Task.class);

		if (!(filter == null) && !(filter.equals(""))) {
			filter = "%"+filter+"%";
			Criterion title = Restrictions.ilike("title", filter, MatchMode.ANYWHERE);
			Criterion description = Restrictions.ilike("description", filter, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(title, description));
		}
		Long records = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return records;
	}

}
