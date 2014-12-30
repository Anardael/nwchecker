/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.dao;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskTheoryLink;

import java.util.LinkedList;
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
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Task> getTasks() {
        List<Task> result = sessionFactory.getCurrentSession().createQuery("from Task")
                .list();
        return result;
    }
    
    @Override
    public void addTask(Task t, LinkedList<TaskData> data, LinkedList<TaskTheoryLink> theory) {
        sessionFactory.getCurrentSession().save(t);
        for (TaskData d : data) {
            d.setTask(t);
            sessionFactory.getCurrentSession().save(d);
        }
        for (TaskTheoryLink task : theory) {
            task.setTask(t);
            sessionFactory.getCurrentSession().save(task);
        }
        System.out.println(t.getId());
    }
    
}
