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
        List<Task> t = (List<Task>) getHibernateTemplate().find("From Task where id=?", id);
        return t.get(0);
    }

    @Override
    public List<Task> getTasks() {
        List<Task> result = (List<Task>) getHibernateTemplate().find("from Task");
        return result;
    }

    @Override
    public void addTask(Task t) {
        getHibernateTemplate().save(t);
    }

}
