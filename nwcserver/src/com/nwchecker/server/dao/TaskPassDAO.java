package com.nwchecker.server.dao;

import java.util.List;
import java.util.Map;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize,
			int pageNumber, Map<String, String> orderParams);

	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize,
			int pageNumber);

	public Long getTaskPassResponseSize(int id);

	public List<TaskPass> getPaginatedSuccessfulTaskPassByTaskId(int id,
			int pageSize, int pageNumber);

	public Long getNumberOfAttempts(int userId, int taskId);

	public Long getTaskPassSuccessfulResponseSize(int id);
}
