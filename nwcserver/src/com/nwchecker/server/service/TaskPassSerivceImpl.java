package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nwchecker.server.dao.TaskPassDAO;
import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.model.TaskPass;

@Service
@SessionAttributes("taskPassOrdering")
public class TaskPassSerivceImpl implements TaskPassService {

	@Autowired
	TaskPassDAO taskPassDAO;


	@Override
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize, String sorting) {
			sorting = sorting.replaceFirst("username", "t.user.displayName");
			List<TaskPass> taskPasses = taskPassDAO.getPaginatedTaskPassByTaskId(taskId, startIndex, pageSize, sorting);
			List<TaskPassJson> result = new ArrayList<TaskPassJson>();
			for (TaskPass taskPass : taskPasses){
				TaskPassJson taskPassJson = TaskPassJson.createTaskPassJson(taskPass);				
				result.add(taskPassJson);
			}
		return result;
	}
	
	@Override
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize) {
			List<TaskPass> taskPasses = taskPassDAO.getPaginatedTaskPassByTaskId(taskId, startIndex, pageSize);
			List<TaskPassJson> result = new ArrayList<TaskPassJson>();
			for (TaskPass taskPass : taskPasses){
				TaskPassJson taskPassJson = TaskPassJson.createTaskPassJson(taskPass);				
				result.add(taskPassJson);
			}
		return result;
	}

	@Override
	public Long getTaskPassSampleSize(int taskId) {
		return taskPassDAO.getTaskPassResponseSize(taskId);
	}
	@Override
	public Long getSuccessfulTaskPassSampleSize(int taskId) {
		return taskPassDAO.getSuccessfulTaskPassResponseSize(taskId);
	}
}
