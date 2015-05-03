package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize, int pageNumber);
	
	public Long getTaskPassResponseSize(int id);
	
	public List<TaskPass> getPaginatedSuccessfulTaskPassByTaskId(int id, int pageSize, int pageNumber);
	
	public Long getNumberOfAttempts(int userId);
	
	public Long getTaskPassSuccessfulResponseSize(int id);
}
