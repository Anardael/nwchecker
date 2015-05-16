package com.nwchecker.server.service;

import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.json.JsonUtil;
import com.nwchecker.server.json.TaskJson;
import com.nwchecker.server.model.Contest.Status;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.utils.PaginationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

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
		return taskDao.getTasksByContestStatus(status);
	}

	@Override
	public PaginationWrapper<TaskJson> getTaskJsonForPagination(
			Status status, int pageSize, int pageNumber) {
		System.out.println(pageNumber + " " + pageSize);
		List<Task> tasks = getPagedTasksByContestStatus(status, pageSize, PaginationWrapper.getFirstResult(pageNumber, pageSize));
		List<TaskJson> paginatedTaskJson = JsonUtil.createJsonList(TaskJson.class, tasks);
		PaginationWrapper<TaskJson> response = new PaginationWrapper<TaskJson>();
		response.setDataList(paginatedTaskJson);
		response.setPageCount(PaginationWrapper.getPageCount(taskDao.getRecordCount(status), pageSize));
		return response;
	}

	@Override
	public List<Task> getPagedTasksByContestStatus(Status status, int pageSize,
			int startIndex) {
		return taskDao.getPagedTasksByContestStatus(status, pageSize, startIndex);
	}
	

	@Override
	public Long getPageCount(Status status, int pageSize) {
		Long records=taskDao.getRecordCount(status);
		Long pageCount;
		if ((records/pageSize)*pageSize == records){
			pageCount = records/pageSize;
		}
		else pageCount = records/pageSize;
		return pageCount;
	}
}
