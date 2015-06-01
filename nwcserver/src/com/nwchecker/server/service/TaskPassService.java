package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.json.TaskPassJson;

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
	 * @param sorting
	 *            A string represents requested sorting.
	 * @return List of Json-like objects ready for display
	 */
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize, String sorting);

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
	 * @return List of Json-like objects ready for display
	 */
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize);

	/**
	 * Get number of solution for a certain task
	 * <p>
	 * 
	 * @param taskId
	 *            Id if the task
	 * @return Number of TaskPasses for task
	 */

	public Long getTaskPassSampleSize(int taskId);

	/**
	 * Get number of solution for task that have successfully passed tests
	 * <p>
	 * 
	 * @param taskId
	 *            Id of task
	 * @return Number of successful TaskPasses
	 */
	public Long getSuccessfulTaskPassSampleSize(int taskId);
}
