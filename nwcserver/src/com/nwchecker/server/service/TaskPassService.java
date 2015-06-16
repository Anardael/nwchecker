package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.model.TaskPass;

/**
 * <h1>Task Pass Service</h1> Service to get task solution submits from database
 * <p>
 * 
 * @author Boris Andreev
 * @version 1.0
 * @since 2015-05-14
 */

public interface TaskPassService {
	
	/**
	 * Get paginated solution records list for task
	 * <p>
	 * 
	 * @param taskId
	 *            Id of the task.
	 * @param startIndex
	 *            Start index of records for current page.
	 * @param pageSize
	 *            Count of maximum expected records.
	 * @param sortingColumn
	 *            A string represents requested sorting column name.
	 * @param sortingOrder
	 *            A string that represents order in which the sorting is made
	 *            (asc or desc)
	 * @return List of solution records for task.
	 */
	public List<TaskPass> getPagedTaskPassesForTask(int taskId, int startIndex,
			int pageSize, String sortingColumn, String sortingOrder,
			String filter);

	/**
	 * Get number of solution for a certain task
	 * <p>
	 * 
	 * @param taskId
	 *            Id if the task
	 * @return Number of TaskPasses for task
	 */

	public Long getTaskPassEntryCount(int taskId, String filter);

	/**
	 * Get number of solution for task that have successfully passed tests
	 * <p>
	 * 
	 * @param taskId
	 *            Id of task
	 * @return Number of successful TaskPasses
	 */
	Long getSuccessfulTaskPassEntryCount(int taskId);

	/**
	 * Get paginated solution records list for task
	 * <p>
	 * 
	 * @param taskId
	 *            Id of the task.
	 * @param startIndex
	 *            Start index of records for current page.
	 * @param pageSize
	 *            Count of maximum expected records.
	 * @param sortingColumn
	 *            A string represents requested sorting column name.
	 * @param sortingOrder
	 *            A string that represents order in which the sorting is made
	 *            (asc or desc)
	 * @return List of Json-like objects ready for display
	 */
	public List<TaskPassJson> getPagedTaskPassJson(int taskId, int startIndex,
			int pageSize, String sortingColumn, String sortingOrder,
			String filter);

	/**
	 * Return double value that represents success rate of user's submissions to
	 * some task (all successful submissions divided by all submissions).
	 * 
	 * @param taskId
	 *            Id of some task
	 * @return Double value that represents success rate.
	 */
	double getTaskSuccessRateById(int taskId);
}
