package com.nwchecker.server.service;

import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.utils.PaginationWrapper;
import com.nwchecker.server.json.JsonUtil;
import com.nwchecker.server.json.TaskJson;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskDAO taskDao;

	@Transactional(readOnly = true)
	@Override
	public Task getTaskById(int id) {
		return taskDao.getTaskById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> getTasksByContestId(int id) {
		return taskDao.getTasksByContestId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> getTasks() {
		return taskDao.getTasks();
	}

	@Override
	@Transactional()
	public void addTask(Task t) {
		taskDao.addTask(t);
	}

	@Override
	@Transactional()
	public void updateTask(Task t) {
		taskDao.updateTask(t);
	}

	@Override
	@Transactional()
	public void deleteTaskById(int id) {
		taskDao.deleteTaskById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public TaskData getTaskData(int id) {
		return taskDao.getTaskData(id);
	}

	@Override
	@Transactional()
	public void deleteTaskData(int id) {
		taskDao.deleteTaskData(id);
	}

	@Override
	public List<Task> getTasksByContestStatus(Status status) {
		LOG.debug("Successfully got tasks for contests with status " + status);
		return taskDao.getTasksByContestStatus(status);
	}

	@Override
	public PaginationWrapper<TaskJson> getTaskJsonForPagination(
			Status status, int pageSize, int pageNumber, String filter) {
		List<Task> tasks = getPagedTasksByContestStatus(status, pageSize, PaginationWrapper.getFirstResult(pageNumber, pageSize), filter);
		List<TaskJson> paginatedTaskJson = JsonUtil.createJsonList(TaskJson.class, tasks);
		PaginationWrapper<TaskJson> response = new PaginationWrapper<TaskJson>();
		response.setDataList(paginatedTaskJson);
		response.setPageCount(PaginationWrapper.getPageCount(taskDao.getRecordCountByContestStatus(status, filter), pageSize));
		return response;
	}

	@Override
	public List<Task> getPagedTasksByContestStatus(Status status, int pageSize,
			int startIndex, String filter) {
		return taskDao.getPagedTasksByContestStatus(status, pageSize, startIndex, filter);
	}
	

	@Override
	public Long getPageCount(Status status, int pageSize, String filter) {
		Long records = taskDao.getRecordCountByContestStatus(status, filter);
		Long pageCount;
		if (records % pageSize == 0) {
			pageCount = records / pageSize;
		} else
			pageCount = records / pageSize + 1;
		LOG.debug("Got page count for tasks that belong to contests with status "
				+ status);
		return pageCount;
	}
}
