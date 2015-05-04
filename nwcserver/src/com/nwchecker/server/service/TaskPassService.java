package com.nwchecker.server.service;

import java.util.Map;


public interface TaskPassService {
	public Map<String, Object> getPagedTaskPassesForTask(int id, int pageSize, int pageNumber);
	public Map<String, Object> getStatisticOfSuccessfulTaskPasses(int taskId, int pageSize, int pageNumber);
	public Long getTaskPassSampleSize(int taskId);
	public Long getTaskPassSuccessfulSampleSize(int taskId);

}
