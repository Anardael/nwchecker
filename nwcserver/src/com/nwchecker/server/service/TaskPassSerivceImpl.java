package com.nwchecker.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.weaver.NewParentTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nwchecker.server.dao.TaskPassDAO;
import com.nwchecker.server.json.JsonUtil;
import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.utils.PaginationWrapper;

@Service
@SessionAttributes("taskPassOrdering")
public class TaskPassSerivceImpl implements TaskPassService {

	@Autowired
	TaskPassDAO taskPassDAO;
	private static final Logger LOG = Logger
			.getLogger(TaskPassSerivceImpl.class);

	@Override
	public List<TaskPass> getPagedTaskPassesForTask(int taskId, int startIndex,
			int pageSize, String sorting) {
		LOG.debug("Attempting to create page of task results for " + taskId
				+ " ordered by " + sorting);
		List<TaskPass> taskPasses = taskPassDAO.getPaginatedTaskPassByTaskId(
				taskId, startIndex, pageSize, sorting);
		return taskPasses;
	}

	@Override
	public Long getTaskPassEntryCount(int taskId) {
		LOG.debug("Got number of solutions for task " + taskId);
		return taskPassDAO.getTaskPassEntryCount(taskId);
	}

	@Override
	public Long getSuccessfulTaskPassEntryCount(int taskId) {
		LOG.debug("Got number of successful solutions for task " + taskId);
		return taskPassDAO.getSuccessfulTaskPassEntryCount(taskId);
	}

	@Override
	public PaginationWrapper<TaskPassJson> getPagedTaskPassJsonForTask(
			int taskId, int startIndex, int pageSize, String sorting) {
		List<TaskPass> taskPasses = getPagedTaskPassesForTask(taskId, startIndex, pageSize, sorting);
		List<TaskPassJson> taskPassJsonList = JsonUtil.createJsonList(TaskPassJson.class,
				taskPasses);
		PaginationWrapper<TaskPassJson> paginatedJson = new PaginationWrapper<TaskPassJson>();
		paginatedJson.setDataList(taskPassJsonList);
		paginatedJson.setRecordCount(getTaskPassEntryCount(taskId));
		LOG.debug("Successfully created page of task results for " + taskId
				+ " ordered by " + sorting);
		return paginatedJson;
	}
}
