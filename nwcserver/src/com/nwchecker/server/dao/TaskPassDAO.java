package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskIdSorted(int id,
			int startIndex, int pageSize, String sorting);

	/**
	 * Return count of TaskPasses for certain Task
	 * 
	 * @param id
	 *            ID of Task.
	 * @return Count of TaskPasses for Task.
	 */
	public Long getTaskPassEntryCount(int id);

	/**
	 * Return count of TaskPasses for certain Task
	 * 
	 * @param id
	 *            ID of Task
	 * @param filter
	 *            String that represents search/filter for TaskPass.
	 * @return Count of TaskPasses that belong to certain Task and fit filter
	 *         criteria.
	 */
	public Long getTaskPassEntryCount(int id, String filter);

	/**
	 * Return count of TaskPasses that have "successful" status to them.
	 * 
	 * @param id
	 *            ID of some Task
	 * @return Count of TaskPasses.
	 */
	public Long getSuccessfulTaskPassEntryCount(int id);

	/**
	 * Return list of TaskPasses that belong to certain Task.
	 * 
	 * @param id
	 *            ID of Task.
	 * @param startIndex
	 *            Start index of records for this page.
	 * @param pageSize
	 *            Size of the page.
	 * @return List of TaskPasses.
	 */

	List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize);
	/**
	 * Return list of TaskPasses that belong to certain Task.
	 * 
	 * @param id
	 *            ID of Task.
	 * @param startIndex
	 *            Start index of records for this page.
	 * @param pageSize
	 *            Size of the page.
	 * @param sortingColumn
	 *            A string represents requested sorting column name.
	 * @param sortingOrder
	 *            A string that represents order in which the sorting is made
	 *            (asc or desc)
	 * @param filter
	 *            String that represents search/filter for TaskPass.
	 * @return
	 */
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize, String sortingColumn, String sortingOrder,
			String filter);

}