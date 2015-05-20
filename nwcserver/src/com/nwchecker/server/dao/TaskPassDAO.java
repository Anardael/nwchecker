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

	public List<TaskPass> getPaginatedTaskPassByTaskIdFiltered(int id,
			int startIndex, int pageSize, String filter);

	public List<TaskPass> getPaginatedTaskPassByTaskIdSortedAndFiltered(int id,
			int startIndex, int pageSize, String sorting, String filter);
}