/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.dao;

import com.nwchecker.server.model.Task;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("TaskDAO")
public class TaskDAOImpl extends HibernateDaoSupport implements TaskDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Task getTaskById(int id) {
        Task t = (Task) getHibernateTemplate().get(Task.class, id);
        return t;
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
        getHibernateTemplate().update(t);
    }

    @Override
    public void deleteTaskById(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().get(Task.class, id));
    }

}
