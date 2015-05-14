package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;

import java.util.List;

/**
 * <h1>Task DAO</h1>
 * DAO for working with Task Entity table.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public interface TaskDAO {

    /**
     * Return Task from database by unique ID.
     * <p>
     *
     * @param id Unique ID of required Task
     * @return Task from DB
     */
    Task getTaskById(int id);

    /**
     * Return all Tasks of some Contest.
     * <p>
     *
     * @param id Unique ID of required Contest
     * @return List of tasks
     */
    List<Task> getTasksByContestId(int id);

    /**
     * Return all Tasks from database
     * <p>
     *
     * @return List of Tasks
     */
    List<Task> getTasks();

    /**
     * Add new Task to database.
     * <p>
     *
     * @param t Task that will be inserted in DB
     */
    void addTask(Task t);

    /**
     * Update existing Task in database.
     * <p>
     *
     * @param t Task that will be updated in DB
     */
    void updateTask(Task t);

    /**
     * Delete existing Task from database.
     * <p>
     *
     * @param id Task that will be removed from DB
     */
    void deleteTaskById(int id);

    /**
     * Return TaskData of some Task.
     * <p>
     *
     * @param id Unique ID of existing Task
     * @return Task data
     */
    TaskData getTaskData(int id);

    /**
     * Delete TaskData of some Task.
     * <p>
     *
     * @param id Unique ID of existing Task
     */
    void deleteTaskData(int id);
    
    List<Task> getTasksByContestStatus(Contest.Status status);
    List<Task> getPagedTasksByContestStatus(Contest.Status status, int pageSize, int startIndex);
    Long getRecordCount(Contest.Status status);
}
