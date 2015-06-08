package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestPassDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.TaskTestResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestResult;

@Service
public class ScoreCalculationServiceImpl implements ScoreCalculationService {
	@Autowired
	private ContestService contestService;
	@Autowired
	private ContestPassService contestPassService;

	@Autowired
	private ContestPassDAO contestPassDAO;

	/*
	 * Checker has two different algorhythms for calculating score of the
	 * finished contest based on contest's type(static or dynamic). Static
	 * contest's score is based on the score that competitor earns passing
	 * tests. Dynamic contest's score is based on a number of criteria: 1.
	 * Number of attemps. 2. Execution time. (non-Javadoc)
	 * 
	 * @see
	 * com.nwchecker.server.service.ScoreCalculationService#calculateScore(int)
	 */

	@Override
	@Transactional
	public void calculateScore(int contestId) {
		Contest contest = contestService.getContestByID(contestId);
		// get all user ContestPasses for this contest:
		List<ContestPass> allContestPasses = contestPassService
				.getContestPasses(contestId);

		// check each ContestPass for each user:
		for (ContestPass contestPass : allContestPasses) {
			// refresh
			contestPass.setPassedCount(0);
			contestPass.setTimePenalty(0);
			
			if (contest.getTypeContest().isDynamic()) {
				calculateDynamicScore(contestPass);
			} else {
				calculateStaticScore(contestPass);
			}

		}
		Collections.sort(allContestPasses);
		int i = 1;
		for (ContestPass c : allContestPasses) {
			c.setRank(i);
			contestPassDAO.updateContestPass(c);
			i++;
		}
	}

	private void calculateStaticScore(ContestPass contestPass) {
		for (TaskPass taskPass : contestPass.getTaskPassList()) {
			for (TaskTestResult taskResult : taskPass.getTestResults()) {
				contestPass.setPassedCount(contestPass.getPassedCount()
						+ taskResult.getRate());
			}
		}
		contestPassDAO.updateContestPass(contestPass);
	}

	private void calculateDynamicScore(ContestPass contestPass) {
		Set<Integer> passedTasks = new HashSet<>();
		// go throw each taskPass:

		for (TaskPass taskPass : contestPass.getTaskPassList()) {
			// if passed first time:
			if (taskPass.isPassed()
					&& !passedTasks.contains(taskPass.getTask().getId())) {
				passedTasks.add(taskPass.getTask().getId());
				// increment passed count for this contestPass:
				contestPass.setPassedCount(contestPass.getPassedCount() + 1);
				// add penalty time:
				contestPass.setTimePenalty(contestPass.getTimePenalty()
						+ taskPass.getPassedMinute());

				// search all failed taskPassed for this Task id:
				for (int i = 0; i < contestPass.getFailedTaskPasses(
						taskPass.getTask().getId()).size(); i++) {
					// for each failed taskPass increment by 20 minute penalty
					// time:
					contestPass
							.setTimePenalty(contestPass.getTimePenalty() + 20);
				}
				contestPassDAO.updateContestPass(contestPass);
			}
		}
	}
}
