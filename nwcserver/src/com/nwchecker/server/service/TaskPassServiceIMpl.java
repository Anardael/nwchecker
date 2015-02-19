package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
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
    public Map<String, Object> checkTask(User user, Task task, int compilerId, byte[] file) {
        TaskPass taskPass = new TaskPass();
        taskPass.setUser(user);
        taskPass.setTask(task);
        //send File and compiler to checker:
        Map<String, Object> checkResult = checkerService.checkTask(task, file, compilerId);
        taskPass.setPassed((boolean) checkResult.get("passed"));
        taskPass.setExecutionTime((int) checkResult.get("time"));
        taskPass.setMemoryUsed((int) checkResult.get("memory"));
        taskPass.setFile(file);
        taskPass.setCompilerMessage(checkResult.get("message") == null ? null : (String) checkResult.get("message"));
        //save to db:
        user.getTaskPass().add(taskPass);
        userService.updateUser(user);
        return checkResult;
    }
}
