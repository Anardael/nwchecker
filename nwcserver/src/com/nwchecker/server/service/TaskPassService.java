package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.json.TaskPassJson;


public interface TaskPassService {
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId, int startIndex, int pageSize, String sorting);
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId, int startIndex, int pageSize);
	public Long getTaskPassSampleSize(int taskId);
}
