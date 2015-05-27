package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskIdSorted(int id,
			int startIndex, int pageSize, String sorting);

	public Long getTaskPassEntryCount(int id);

	public Long getTaskPassEntryCount(int id, String filter);

	public Long getSuccessfulTaskPassEntryCount(int id);

	List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize);

	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize, String sortingColumn, String sortingOrder, String filter);
}