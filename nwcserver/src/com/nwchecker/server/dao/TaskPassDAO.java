package com.nwchecker.server.dao;

import java.util.List;

import com.nwchecker.server.model.TaskPass;

public interface TaskPassDAO {
	public List<TaskPass> getPaginatedTaskPassByTaskId(int id, int pageSize, int pageNumber);
	
	public Long getTaskPassSampleSize(int id);
	
	public List<TaskPass> getPaginatedSuccessfulTaskPassByTaskId(int id, int pageSize, int pageNumber);
	
	public Long getNumberOfAttempts(int userId, int taskId);
	
	public Long getTaskPassSuccessfulSampleSize(int id);	

}
