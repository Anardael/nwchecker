/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.dao;

import com.nwchecker.server.model.Task;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Роман
 */
@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Task getTaskById(int id) {
        Task t = (Task) sessionFactory.getCurrentSession().createQuery("from Task where id=?")
                .setParameter(0, id).list().get(0);
        return t;
    }

    @Override
    public List<Task> getTasks() {
        @SuppressWarnings("unchecked")
		List<Task> result = sessionFactory.getCurrentSession().createQuery("from Task")
                .list();
        return result;
    }

    @Override
    public void addTask(Task t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
