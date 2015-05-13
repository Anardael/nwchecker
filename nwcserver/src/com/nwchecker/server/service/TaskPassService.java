package com.nwchecker.server.service;

import java.util.List;
import java.util.Map;

import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.utils.OrderParams;


public interface TaskPassService {
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId, int startIndex, int pageSize, String sorting);
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId, int startIndex, int pageSize);
	public Map<String, Object> getStatisticOfSuccessfulTaskPasses(int taskId, int pageSize, int pageNumber);
	public Long getTaskPassSampleSize(int taskId);
	public Long getTaskPassSuccessfulSampleSize(int taskId);
	public Map<String, String> parseOrderParams(OrderParams orderParams);

}
