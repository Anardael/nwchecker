package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize,
			int pageNumber, String sorting);

	public Long getTaskPassEntryCount(int id);

	public Long getSuccessfulTaskPassEntryCount(int id);
}