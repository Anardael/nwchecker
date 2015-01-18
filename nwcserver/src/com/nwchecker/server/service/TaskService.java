/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskTheoryLink;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author �����
 */
public interface TaskService {

    Task getTaskById(int id);
    
    List<Task> getTasksByContestId(int id);

    List<Task> getTasks();

    void addTask(Task t);
    
    void updateTask(Task t);
    
    void deleteTaskById(int id);
}
