package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.json.TaskPassJson;

/**
 * <h1>Task Pass Service</h1> Service to get TaskPasses from database
 * <p>
 * 
 * @author Boris Andreev
 * @version 1.0
 * @since 2015-05-14
 */

public interface TaskPassService {
	/**
	 * Get paginated TaskPasses for task
	 * 
	 * @param taskId
	 *            Id of the task.
	 * @param startIndex
	 *            Start index of records for current page.
	 * @param pageSize
	 *            Count of maximum expected records.
	 * @param sorting
	 *            A string represents requested sorting.
	 * @return list of Json-like objects ready for display
	 */
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize, String sorting);

	/**
	 * Get paginated TaskPasses for task
	 * 
	 * @param taskId
	 *            Id of the task.
	 * @param startIndex
	 *            Start index of records for current page.
	 * @param Count
	 *            of maximum expected records.
	 * @return list of Json-like objects ready for display
	 */
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize);

	/**
	 * Get number of TaskPasses for a certain task
	 * 
	 * @param taskId
	 *            id if the task
	 * @return number of TaskPasses for task
	 */

	public Long getTaskPassSampleSize(int taskId);

	/**
	 * Get number of TaskPasses for task that have successfully passed tests
	 * 
	 * @param taskId
	 *            id of task
	 * @return number of successful TaskPasses
	 */
	public Long getSuccessfulTaskPassSampleSize(int taskId);
}
