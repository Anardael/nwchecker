package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;

import java.util.List;

/**
 * <h1>Task Service</h1> Service that can save, update and delete Tasks.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public interface TaskService {

	/**
	 * Return Task from database by unique ID.
	 * <p>
	 *
	 * @param id
	 *            Unique ID of required Task
	 * @return Task from DB
	 */
	Task getTaskById(int id);

	/**
	 * Return all Tasks of some Contest.
	 * <p>
	 *
	 * @param id
	 *            Unique ID of required Contest
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
	 * @param t
	 *            Task that will be inserted in DB
	 */
	void addTask(Task t);

	/**
	 * Update existing Task in database.
	 * <p>
	 *
	 * @param t
	 *            Task that will be updated in DB
	 */
	void updateTask(Task t);

	/**
	 * Delete existing Task from database.
	 * <p>
	 *
	 * @param id
	 *            Task that will be removed from DB
	 */
	void deleteTaskById(int id);

	/**
	 * Return TaskData of some Task.
	 * <p>
	 *
	 * @param id
	 *            Unique ID of existing Task
	 * @return Task data
	 */
	TaskData getTaskData(int id);

	/**
	 * Delete TaskData of some Task.
	 * <p>
	 *
	 * @param id
	 *            Unique ID of existing Task
	 */
	void deleteTaskData(int id);

	/**
	 * Get list of tasks that belong to contests with certain status
	 * 
	 * @param status
	 *            status of the contests
	 * @return List of tasks that belong to contests with certain status
	 * @author Boris Andreev
	 */
	List<Task> getTasksByContestStatus(Contest.Status status);

	/**
	 * Get paginated list of tasks that belong to contests with certain status
	 * 
	 * @param status
	 *            status of the contests
	 * @param pageSize
	 *            Count of maximum expected records
	 * @param pageNumber
	 *            number of the requested page
	 * @return List of tasks that belong to contests with certain status,
	 *         limited by pageSize and PageNumber
	 * @author Boris Andreev
	 */
	List<Task> getPagedTasksByContestStatus(Contest.Status status,
			int pageSize, int pageNumber);

	/**
	 * Get number of pages that can fit the list of tasks that belong to
	 * contests with certain status
	 * 
	 * @param status
	 *            status of contests
	 * @param pageSize
	 *            count of maximum expected records
	 * @return number of pages
	 * @author Boris Andreev
	 */
	Long getPageCount(Contest.Status status, int pageSize);
}
