package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestPassDAO;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 11.02.2015.
 */
@Service(value = "TaskPassService")
public class ContestPassServiceIMpl implements ContestPassService {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CheckerService checkerService;
    @Autowired
    private ContestPassDAO contestPassDAO;

    @Override
    @Transactional
    public void saveContestPass(ContestPass contestPass) {
        contestPassDAO.saveContestPass(contestPass);
    }

    @Override
    @Transactional
    public void updateContestPass(ContestPass contestPass) {
        contestPassDAO.updateContestPass(contestPass);
    }

    @Override
    @Transactional
    public Map<String, Object> checkTask(boolean save, ContestPass contestPass, Task task, int compilerId, byte[] file) {
        TaskPass taskPass = new TaskPass();
        taskPass.setContestPass(contestPass);
        taskPass.setTask(task);

        //send File and compiler to checker:
        Map<String, Object> checkResult = checkerService.checkTask(task, file, compilerId);

        taskPass.setPassed((boolean) checkResult.get("passed"));
        taskPass.setExecutionTime((int) checkResult.get("time"));
        taskPass.setMemoryUsed((int) checkResult.get("memory"));
        taskPass.setFile(file);
        taskPass.setCompilerMessage(checkResult.get("message") == null ? null : (String) checkResult.get("message"));
        if (save == true) {
            contestPass.getTaskPassList().add(taskPass);
            updateContestPass(contestPass);
        }
        return checkResult;
    }

    @Override
    public List<ContestPass> getContestPasses(int contestId) {
        return contestPassDAO.getContestPasses(contestId);
    }
}
