package com.nwchecker.server.dao;

import com.nwchecker.server.model.TaskSolution;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("TaskSolutionDAO")
public class TaskSolutionDAOImpl extends HibernateDaoSupport implements TaskSolutionDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void addTaskSolution(TaskSolution t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    @Transactional
    public void updateTaskSolution(TaskSolution t) {
        getHibernateTemplate().merge(t);
    }

    @Override
    @Transactional
    public List<TaskSolution> getTaskSolutionsByContestId(int id) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from TaskSolution ts where ts.contest.id =:id");
        query.setParameter("id", id);
        return (List<TaskSolution>) query.list();
    }
}
