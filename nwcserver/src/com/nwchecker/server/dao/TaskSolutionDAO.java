package com.nwchecker.server.dao;

import com.nwchecker.server.model.TaskSolution;

import java.util.List;

public interface TaskSolutionDAO {

    void addTaskSolution(TaskSolution t);

    void updateTaskSolution(TaskSolution t);

    List<TaskSolution> getTaskSolutionsByContestId(int id);

}
