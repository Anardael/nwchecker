/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.service;

import com.nwchecker.server.dao.TaskDao;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Transactional(readOnly = true)
    @Override
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> getTasks() {
        return taskDao.getTasks();
    }

    @Override
    public void addTask(Task t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
