package com.nwchecker.server.service;

import com.nwchecker.server.dao.TaskSolutionDAO;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskSolution;
import com.nwchecker.server.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskSolutionServiceImpl implements TaskSolutionService {

    private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskSolutionDAO taskSolutionDao;

    @Override
    public void addTaskSolution(TaskSolution t) {
        taskSolutionDao.addTaskSolution(t);
    }

    @Override
    public void updateTaskSolution(TaskSolution t) {
        taskSolutionDao.updateTaskSolution(t);
    }

    @Override
    public List<TaskSolution> getTaskSolutionsByContestId(int id) {
        return taskSolutionDao.getTaskSolutionsByContestId(id);
    }

    @Override
    public TaskSolution getExistingTaskSolution(User user, Task task) {
        List<TaskSolution> taskSolutions = getTaskSolutionsByContestId(task.getContest().getId());

        if (taskSolutions != null) {
            for (TaskSolution ts : taskSolutions) {
                if (ts.getUser().equals(user) && ts.getTask().equals(task)) {
                    return ts;
                }
            }
        }
        return null;
    }
}
