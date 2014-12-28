/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import java.util.List;

/**
 *
 * @author Роман
 */
public interface TaskService {

    Task getTaskById(int id);

    List<Task> getTasks();

    void addTask(Task t);
}
