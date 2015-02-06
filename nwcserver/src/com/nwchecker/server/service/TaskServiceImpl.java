/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.service;

import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDao;

    @Override
    public void setDAO(TaskDAO dao) {
        this.taskDao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByContestId(int id) {
        return taskDao.getTasksByContestId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasks() {
        return taskDao.getTasks();
    }

    @Override
    @Transactional()
    public void addTask(Task t) {
        taskDao.addTask(t);
    }

    @Override
    @Transactional()
    public void updateTask(Task t) {
        taskDao.updateTask(t);
    }

    @Override
    @Transactional()
    public void deleteTaskById(int id) {
        taskDao.deleteTaskById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskData getTaskData(int id) {
        return taskDao.getTaskData(id);
    }

    @Override
    @Transactional()
    public void deleteTaskData(int id) {
        taskDao.deleteTaskData(id);
    }
}
