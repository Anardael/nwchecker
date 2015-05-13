package com.nwchecker.server.service;

import java.util.List;

import com.nwchecker.server.model.Task;

public interface ArchiveService {
	public List<Task> getArchivedTasks();
}