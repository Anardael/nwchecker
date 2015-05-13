package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;

@Service
public class ArchiveServiceImpl implements ArchiveService {
	@Autowired
	ContestService contestService;
	@Autowired
	TaskDAO taskDAO;

	@Override
	public List<Task> getArchivedTasks() {
    	//List<Contest> archivedContests = contestService.getContestByStatus(Contest.Status.ARCHIVE);
    	List<Task> tasks = taskDAO.getTasksByContestStatus(Contest.Status.ARCHIVE);
    	/*for (Contest c : archivedContests){
    		tasks.addAll(c.getTasks());
    	}*/
    return tasks;
	}
/*	public List<Task> getPagedArchivedTasks(int pageNumber, int size) {
    	List<Task> archivedContests = contestService.getPagedContestByStatus(Contest.Status.ARCHIVE, pageNumber, size);
    	List<Task> tasks = new ArrayList<Task>();
    	for (Contest c : archivedContests){
    		tasks.addAll(c.getTasks());
    	}
    return archivedContests;
	}
*/
}