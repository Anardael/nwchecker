package com.nwchecker.server.service;

import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.dao.ContestPassDAO;
import com.nwchecker.server.dao.UserDAO;
import com.nwchecker.server.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(value = "TaskPassService")
public class ContestPassServiceImpl implements ContestPassService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CheckerService checkerService;
    @Autowired
    private ContestPassDAO contestPassDAO;
    @Autowired
    private CompilerDAO compilerDAO;

    @Override
    public void saveContestPass(ContestPass contestPass) {
        contestPassDAO.saveContestPass(contestPass);
    }

    @Override
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

    @Override
    public Map<Integer, Boolean> getTaskResultsForContestByUserName(String userName, Contest contest){
        User user = userDAO.getUserByUsername(userName);
        if (checkContestPassByUserName(userName, contest)){
            ContestPass contestPass = contestPassDAO.getContestPassByUserIdAndContestId(user.getUserId(), contest.getId());
            Map<Integer, Boolean> taskResults = new LinkedHashMap<>();

            for (TaskPass taskPass : contestPass.getTaskPassList()) {
                int taskId = taskPass.getTask().getId();
                boolean isPassed = taskPass.isPassed();
                //if ((not contains) or (new result success))
                if (!taskResults.containsKey(taskId) || (!taskResults.get(taskId) && isPassed)) {
                    taskResults.put(taskId, isPassed);
                }
            }

            return taskResults;
        } else  {
            ContestPass contestPass = new ContestPass();
            contestPass.setContest(contest);
            contestPass.setUser(user);
            contestPassDAO.saveContestPass(contestPass);

            return null;
        }
    }

    @Override
    public boolean checkContestPassByUserName(String userName, Contest contest){
        User user = userDAO.getUserByUsername(userName);
        for (ContestPass contestPass : user.getContestPassList()) {
            if (contestPass.getContest().equals(contest)) {
                return true;
            }
        }
        return false;
    }
}
