package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.utils.PaginationWrapper;

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
	 * <p>
	 * 
	 * @param status
	 *            status of the contests
	 * @return List of tasks that belong to contests with certain status
	 * @author Boris Andreev
	 */
	List<Task> getTasksByContestStatus(Contest.Status status);

	/**
	 * Get list of tasks that belong to contests with certain status for
	 * pagination
	 * <p>
	 * 
	 * @param status
	 *            Status of the contest to which the tasks belong.
	 * @param startIndex
	 *            Index of the record first requested record.
	 * @param pageSize
	 *            Number of records requested.
	 * @param sortingColumn
	 *            Name of attribute for sorting
	 * @param sortingOrder
	 *            sorting order(asc or desc)
	 * @param filter
	 *            String that represents search query of the results.
	 * @return List of tasks that belong to contests with certain status,
	 *         limited by pageSize and pageNumber
	 */
	List<Task> getPagedTasksByContestStatus(Contest.Status status,
			int startIndex, int pageSize, String sortingColumn,
			String sortingOrder, String filter);

	/**
	 * Tasks wrapped for pagination that belong to contests with certain status
	 * <p>
	 * 
	 * @param status
	 *            Status of the contest to which the tasks belong.
	 * @param startIndex
	 *            Index of the record first requested record.
	 * @param pageSize
	 *            Number of records requested.
	 * @param sortingColumn
	 *            Name of attribute for sorting
	 * @param sortingOrder
	 *            Sorting order(asc or desc)
	 * @param filter
	 *            String that represents search query of the results.
	 * @return A wrapper that contains list of tasks and total number of records
	 *         that fit the status and filter criteria.
	 */
	PaginationWrapper<Task> getTaskWrapperForPagination(Contest.Status status,
			int startIndex, int pageSize, String sortingColumn,
			String sortingOrder, String filter);

	/**
	 * Total number of Tasks that fit the criteria
	 * <p>
	 * 
	 * @param status
	 *            Status of the contest to which the tasks belong.
	 * @param filter
	 *            String that represents search query of the results.
	 * @return Total number of Tasks that fit the criteria.
	 */
	Long getRecordCount(Contest.Status status, String filter);
}
