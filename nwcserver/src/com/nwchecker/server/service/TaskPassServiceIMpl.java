package com.nwchecker.server.service;

import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Роман on 11.02.2015.
 */
@Service(value = "TaskPassService")
public class TaskPassServiceIMpl implements TaskPassService {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CheckerService checkerService;

    @Override
    public boolean checkTask(User user, int taskId, int compilerId, byte[] file) {

        TaskPass currentTaskPass = null;
        //if user has already TaskPass for this task:
        for (TaskPass taskPass : user.getTaskPass()) {
            if (taskPass.getTask().getId() == taskId) {
                currentTaskPass = taskPass;
                user.getTaskPass().remove(taskPass);
                break;
            }
        }
        //if user has no TaskPass for this task- create new TaskPass:
        if (currentTaskPass == null) {
            currentTaskPass = new TaskPass();
            currentTaskPass.setUser(user);
            currentTaskPass.setTask(taskService.getTaskById(taskId));
        }
        //send File and compiler to checker:
        Map<String, Object> checkResult = checkerService.checkTask(taskId, file, compilerId);
        if ((boolean) checkResult.get("passed") == true) {
            currentTaskPass.setPassed(true);
            currentTaskPass.setTimeSpent((int) checkResult.get("time"));
            currentTaskPass.setMemoryUsed((int) checkResult.get("memory"));
        } else {
            currentTaskPass.setFailTimes(currentTaskPass.getFailTimes() + 1);
        }
        //save to db:
        user.getTaskPass().add(currentTaskPass);
        userService.updateUser(user);
        //return passed or no:
        return (boolean) checkResult.get("passed");
    }
}
