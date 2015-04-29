package com.nwchecker.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.TaskPassDAO;
import com.nwchecker.server.model.TaskPass;

@Service
public class TaskPassSerivceImpl implements TaskPassService {

	@Autowired
	TaskPassDAO taskPassDAO;

	@Override
	public Map<String, Object> getPagedTaskPassesForContest(int id,
			int pageSize, int pageNumber) {
		List<TaskPass> taskPassList = taskPassDAO.getPaginatedTaskPassByTaskId(
				id, pageSize, pageNumber);
		Long lastPage = taskPassDAO.getTaskPassResponseSize(id) % pageSize;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskPassList", taskPassList);
		result.put("lastPage", lastPage);
		return result;
	}

}
