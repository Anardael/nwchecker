package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.TaskPassDAO;
import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.model.TaskPass;

@Service
public class TaskPassSerivceImpl implements TaskPassService {

	@Autowired
	TaskPassDAO taskPassDAO;

	@Override
	public Map<String, Object> getPagedTaskPassesForTask(int taskId, int pageSize,
			int pageNumber, Map<String, String> orderParams) {
		List<TaskPass> taskPassList = taskPassDAO.getPaginatedTaskPassByTaskId(
				taskId, pageSize, pageNumber, orderParams);
		List<TaskPassJson> taskPassJsonList = new ArrayList<TaskPassJson>();
		for (TaskPass taskPass : taskPassList) {
			Long attempts = taskPassDAO.getNumberOfAttempts(taskPass.getUser()
					.getUserId(), taskId);
			taskPassJsonList.add(TaskPassJson.createTaskPassJson(taskPass,
					attempts));
		}
		Long lastPage = taskPassDAO.getTaskPassResponseSize(taskId);
		if ((lastPage / pageSize) * pageSize == lastPage) {
			lastPage = lastPage / pageSize;
		} else {
			lastPage = lastPage / pageSize + 1;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskPassList", taskPassJsonList);
		result.put("lastPage", lastPage);
		return result;
	}

	@Override
	public Map<String, Object> getPagedTaskPassesForTask(int taskId,
			int pageSize, int pageNumber) {
		List<TaskPass> taskPassList = taskPassDAO.getPaginatedTaskPassByTaskId(
				taskId, pageSize, pageNumber);
		List<TaskPassJson> taskPassJsonList = new ArrayList<TaskPassJson>();
		for (TaskPass taskPass : taskPassList) {
			Long attempts = taskPassDAO.getNumberOfAttempts(taskPass.getUser()
					.getUserId(), taskId);
			taskPassJsonList.add(TaskPassJson.createTaskPassJson(taskPass,
					attempts));
		}
		Long lastPage = taskPassDAO.getTaskPassResponseSize(taskId);
		if ((lastPage / pageSize) * pageSize == lastPage) {
			lastPage = lastPage / pageSize;
		} else {
			lastPage = lastPage / pageSize + 1;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskPassList", taskPassJsonList);
		result.put("lastPage", lastPage);
		return result;
	}

	public Map<String, Object> getStatisticOfSuccessfulTaskPasses(int taskId,
			int pageSize, int pageNumber) {
		List<TaskPass> taskPassList = taskPassDAO
				.getPaginatedSuccessfulTaskPassByTaskId(taskId, pageSize,
						pageNumber);
		List<TaskPassJson> taskPassJsonList = new ArrayList<TaskPassJson>();
		for (TaskPass taskPass : taskPassList) {
			Long attempts = taskPassDAO.getNumberOfAttempts(taskPass.getUser()
					.getUserId(), taskId);
			taskPassJsonList.add(TaskPassJson.createTaskPassJson(taskPass,
					attempts));
		}
		Long lastPage = taskPassDAO.getTaskPassSuccessfulResponseSize(taskId);
		if ((lastPage % pageSize) * pageSize == lastPage) {
			lastPage = lastPage / pageSize;
		} else {
			lastPage = lastPage / pageSize + 1;
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("lastPage", lastPage);
		response.put("taskPassList", taskPassJsonList);
		return response;
	}

	@Override
	public Long getTaskPassSampleSize(int taskId) {
		return taskPassDAO.getTaskPassResponseSize(taskId);
	}

	@Override
	public Long getTaskPassSuccessfulSampleSize(int taskId) {
		return taskPassDAO.getTaskPassSuccessfulResponseSize(taskId);
	}
}
