package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskSolution;
import com.nwchecker.server.model.User;

import java.util.List;

public interface TaskSolutionService {

    void addTaskSolution(TaskSolution t);

    void updateTaskSolution(TaskSolution t);

    List<TaskSolution> getTaskSolutionsByContestId(int id);

    TaskSolution getExistingTaskSolution(User user, Task task);
}
