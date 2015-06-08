package com.nwchecker.server.service;

import com.nwchecker.server.dao.CompilerDAO;
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
	@Autowired
	private TaskPassService taskPassService;
	@Autowired
	private ScoreCalculationService scoreCalculationService;

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
	public Map<String, Object> checkTask(ContestPass contestPass,
			Task task, int compilerId, byte[] userSolution, User user) {
		if (contestPass == null){
			HashMap<String, Object> response = new HashMap<String, Object>();
			response.put("accessDenied", true);
			return response;
		}

		// send File and compiler to checker:
		TaskPass taskPass = new TaskPass();
		Map<String, Object> checkResult = checkerService.checkTask(task,
				compilerId, userSolution, taskPass);
		
		if (task.getContest().getStatus() == Contest.Status.GOING) {			
			taskPass.setUser(user);
			taskPass.setContestPass(contestPass);
			taskPass.setTask(task);
			taskPass.setPassed((boolean) checkResult.get("passed"));
			taskPass.setFile(userSolution);
			taskPass.setCompiler(compilerDAO.getCompilerById(compilerId));
			taskPass.setTestResults((List<TaskTestResult>) checkResult.remove("results"));		
			// get passed minute:
			long millis = System.currentTimeMillis()
					- taskPass.getTask().getContest().getStarts().getTime();
			long minute = millis / 1000 / 60;
			taskPass.setPassedMinute((int) minute);
			addTaskPass(contestPass, taskPass, task);
			updateContestPass(contestPass);			
		}
		if (task.getContest().getTypeContest().isDynamic()){
			scoreCalculationService.calculateScore(task.getContest().getId());
		}
		return checkResult;
	}

	public void addTaskPass(ContestPass contestPass, TaskPass taskPass,
			Task task) {
		boolean contains = false;
		if (!(contestPass.getContest().getTypeContest().isDynamic())) {
			for (int index = 0; index < contestPass.getTaskPassList().size(); index++) {
				if (contestPass.getTaskPassList().get(index).getTask()
						.equals(task)) {
					contains = true;
					TaskPass oldTaskPass = contestPass.getTaskPassList().get(
							index);
					taskPass.setId(oldTaskPass.getId());
					contestPass.getTaskPassList().set(index, taskPass);
					Iterator<TaskTestResult> newTestData = taskPass
							.getTestResults().iterator();
					Iterator<TaskTestResult> oldTestData = oldTaskPass
							.getTestResults().iterator();
					while (newTestData.hasNext() && oldTestData.hasNext()) {
						newTestData.next().setId(oldTestData.next().getId());
					}

				}
			}
		}
		if (!contains) {
			contestPass.getTaskPassList().add(taskPass);
		}
	}

	@Override
	public List<ContestPass> getContestPasses(int contestId) {
		return contestPassDAO.getContestPasses(contestId);
	}

	@Override
	public Map<Integer, Boolean> getTaskResultsForContestByUserName(
			String userName, Contest contest) {
		User user = userDAO.getUserByUsername(userName);
		if (checkContestPassByUserName(userName, contest)) {
			ContestPass contestPass = contestPassDAO
					.getContestPassByUserIdAndContestId(user.getUserId(),
							contest.getId());
			Map<Integer, Boolean> taskResults = new LinkedHashMap<>();

			for (TaskPass taskPass : contestPass.getTaskPassList()) {
				int taskId = taskPass.getTask().getId();
				boolean isPassed = taskPass.isPassed();
				// if ((not contains) or (new result success))
				if (!taskResults.containsKey(taskId)
						|| (!taskResults.get(taskId) && isPassed)) {
					taskResults.put(taskId, isPassed);
				}
			}

			return taskResults;
		} else {
			ContestPass contestPass = new ContestPass();
			contestPass.setContest(contest);
			contestPass.setUser(user);
			contestPassDAO.saveContestPass(contestPass);

			return null;
		}
	}

	@Override
	public boolean checkContestPassByUserName(String userName, Contest contest) {
		User user = userDAO.getUserByUsername(userName);
		for (ContestPass contestPass : user.getContestPassList()) {
			if (contestPass.getContest().equals(contest)) {
				return true;
			}
		}
		return false;
	}
}
