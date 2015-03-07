package com.nwchecker.server.dao;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

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
        List<Task> t = (List<Task>) getHibernateTemplate().find("From Task where contest_id=?", id);
        return t;
    }
    
    @Override
    public List<Task> getTasks() {
    	@SuppressWarnings("unchecked")
        List<Task> result = (List<Task>) getHibernateTemplate().find("from Task");
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
        getHibernateTemplate().delete(getHibernateTemplate().get(Task.class, id));
    }
    
    @Override
    public TaskData getTaskData(int id) {
        return getHibernateTemplate().get(TaskData.class, id);
    }
    
    @Override
    public void deleteTaskData(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().get(TaskData.class, id));
    }
    
}
