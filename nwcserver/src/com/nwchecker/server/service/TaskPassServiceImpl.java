package com.nwchecker.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nwchecker.server.dao.TaskPassDAO;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.utils.PaginationWrapper;

@Service
@SessionAttributes("taskPassOrdering")
public class TaskPassServiceImpl implements TaskPassService {

	@Autowired
	TaskPassDAO taskPassDAO;
	private static final Logger LOG = Logger
			.getLogger(TaskPassServiceImpl.class);

	@Override
	public List<TaskPass> getPagedTaskPassesForTask(int taskId, int startIndex,
			int pageSize, String sortingColumn, String sortingOrder, String filter) {
		LOG.debug("Attempting to create page of task results for " + taskId
				+ " ordered by " + sortingColumn);
		List<TaskPass> taskPasses = taskPassDAO.getPaginatedTaskPassByTaskId(taskId, startIndex, pageSize, sortingColumn, sortingOrder, filter);
		return taskPasses;
	}

	@Override
	public Long getTaskPassEntryCount(int taskId, String filter) {
		LOG.debug("Got number of solutions for task " + taskId);
		if (!(filter == null) && !(filter.equals(""))) {
			return taskPassDAO.getTaskPassEntryCount(taskId, filter);
		}
		return taskPassDAO.getTaskPassEntryCount(taskId);
	}

	@Override
	public Long getSuccessfulTaskPassEntryCount(int taskId) {
		LOG.debug("Got number of successful solutions for task " + taskId);
		return taskPassDAO.getSuccessfulTaskPassEntryCount(taskId);
	}

	@Override
	public PaginationWrapper<TaskPass> getPagedTaskPassJsonForTask(
			int taskId, int startIndex, int pageSize, String sortingColumn, String sortingOrder, String filter) {
		List<TaskPass> taskPasses = getPagedTaskPassesForTask(taskId,
				startIndex, pageSize, sortingColumn, sortingOrder, filter);
		PaginationWrapper<TaskPass> paginatedTaskPass = new PaginationWrapper<TaskPass>();
		paginatedTaskPass.setDataList(taskPasses);
		paginatedTaskPass.setRecordCount(getTaskPassEntryCount(taskId, filter));
		LOG.debug("Successfully created page of task results for " + taskId
				+ " ordered by " + sortingColumn + " " + sortingOrder);
		return paginatedTaskPass;
	}

    @Override
    public double getTaskRateById(int taskId){
        long successfulPassCount = taskPassDAO.getSuccessfulTaskPassEntryCount(taskId);
        long allPassCount = taskPassDAO.getTaskPassEntryCount(taskId);
        if(allPassCount != 0){
            return ((double) successfulPassCount) / ((double) allPassCount);
        } else{
            return 0;
        }
    }
}