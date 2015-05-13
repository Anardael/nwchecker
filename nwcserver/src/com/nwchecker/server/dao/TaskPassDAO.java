package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize,
			int pageNumber, String sorting);

	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int startIndex,
			int pageSize);

	public Long getTaskPassResponseSize(int id);
}