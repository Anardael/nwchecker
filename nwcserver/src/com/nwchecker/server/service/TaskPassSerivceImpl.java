package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
	private static final Logger LOG = Logger.getLogger(TaskPassSerivceImpl.class);
	

	@Override
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize, String sorting) {
			LOG.debug("Attempting to create page of task results for "+ taskId + " ordered by " + sorting);
			sorting = sorting.replaceFirst("username", "t.user.displayName");
			List<TaskPass> taskPasses = taskPassDAO.getPaginatedTaskPassByTaskId(taskId, startIndex, pageSize, sorting);
			List<TaskPassJson> result = new ArrayList<TaskPassJson>();
			for (TaskPass taskPass : taskPasses){
				TaskPassJson taskPassJson = TaskPassJson.createTaskPassJson(taskPass);				
				result.add(taskPassJson);
			}
			LOG.debug("Successfully created page of task results for "+ taskId + " ordered by " + sorting);
		return result;
	}
	
	@Override
	public List<TaskPassJson> getPagedTaskPassesForTask(int taskId,
			int startIndex, int pageSize) {
			LOG.debug("Attempting to create page of task results for "+ taskId);
			List<TaskPass> taskPasses = taskPassDAO.getPaginatedTaskPassByTaskId(taskId, startIndex, pageSize);
			List<TaskPassJson> result = new ArrayList<TaskPassJson>();
			for (TaskPass taskPass : taskPasses){
				TaskPassJson taskPassJson = TaskPassJson.createTaskPassJson(taskPass);				
				result.add(taskPassJson);
			}
			LOG.debug("Successfully created page of task results for "+ taskId);
		return result;
	}

	@Override
	public Long getTaskPassSampleSize(int taskId) {
		LOG.debug("Got number of solutions for task "+ taskId);
		return taskPassDAO.getTaskPassResponseSize(taskId);
	}
	@Override
	public Long getSuccessfulTaskPassSampleSize(int taskId) {
		LOG.debug("Got number of successful solutions for task "+ taskId);
		return taskPassDAO.getSuccessfulTaskPassResponseSize(taskId);
	}
}
