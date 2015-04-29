package com.nwchecker.server.service;

import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.dao.ContestPassDAO;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(value = "TaskPassService")
public class ContestPassServiceImpl implements ContestPassService {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CheckerService checkerService;
    @Autowired
    private ContestPassDAO contestPassDAO;
    @Autowired
    private CompilerDAO compilerDAO;

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
    public Map<String, Object> checkTask(boolean save, ContestPass contestPass, Task task, int compilerId, byte[] file, User user) {

        //send File and compiler to checker:
        Map<String, Object> checkResult = checkerService.checkTask(task, file, compilerId);
        
        TaskPass taskPass = new TaskPass();
        taskPass.setUser(user);
        taskPass.setContestPass(contestPass);
        taskPass.setTask(task);
        taskPass.setPassed((boolean) checkResult.get("passed"));
        taskPass.setExecutionTime((int) checkResult.get("time"));
        taskPass.setMemoryUsed((int) checkResult.get("memory"));
        taskPass.setFile(file);
        taskPass.setCompiler(compilerDAO.getCompilerById(compilerId));
        taskPass.setCompilerMessage(checkResult.get("message") == null ? null : (String) checkResult.get("message"));
        //get passed minute:
        long millis = System.currentTimeMillis() - taskPass.getTask().getContest().getStarts().getTime();
        long minute = millis / 1000 / 60;
        taskPass.setPassedMinute((int) minute);
        
        if (save) {
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
