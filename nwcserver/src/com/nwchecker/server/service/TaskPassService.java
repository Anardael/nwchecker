package com.nwchecker.server.service;

import java.util.Map;

import com.nwchecker.server.utils.OrderParams;


public interface TaskPassService {
	public Map<String, Object> getPagedTaskPassesForTask(int taskId, int pageSize, int pageNumber, Map<String, String> orderParams);
	public Map<String, Object> getPagedTaskPassesForTask(int taskId, int pageSize, int pageNumber);
	public Map<String, Object> getStatisticOfSuccessfulTaskPasses(int taskId, int pageSize, int pageNumber);
	public Long getTaskPassSampleSize(int taskId);
	public Long getTaskPassSuccessfulSampleSize(int taskId);
	public Map<String, String> parseOrderParams(OrderParams orderParams);

}
