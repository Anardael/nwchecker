package com.nwchecker.server.utils;

import com.nwchecker.server.model.Contest;

import java.util.Comparator;

/**
 * <h1>Contest Comparator</h1> Comparator that used for sorting contests with
 * status "GOING" or "RELEASE" in excution time order.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public class ContestComparator implements Comparator<Contest> {
	@Override
	public int compare(Contest contest1, Contest contest2) {
		if (!(contest1.getStatus().equals(Contest.Status.RELEASE) || contest1
				.getStatus().equals(Contest.Status.GOING)
				&& (contest2.getStatus().equals(Contest.Status.RELEASE) || contest2
						.getStatus().equals(Contest.Status.GOING)))) {
			throw new IllegalArgumentException();
		}
		long firstContestExecutionTime = 0;
		long secongContestExecutionTime = 0;

		if (contest1.getStatus().equals(Contest.Status.RELEASE)) {
			// if release- execution time==start time:
			firstContestExecutionTime = contest1.getStarts().getTime();
		} else {
			// if status==GOING- execution time= start date + duration:
			firstContestExecutionTime = contest1.getStarts().getTime()
					+ contest1.getDuration().getTime() + 2 * 60 * 60 * 1000;
		}

		if (contest2.getStatus().equals(Contest.Status.RELEASE)) {
			secongContestExecutionTime = contest2.getStarts().getTime();
		} else {
			secongContestExecutionTime = contest2.getStarts().getTime()
					+ contest2.getDuration().getTime() + 2 * 60 * 60 * 1000;
		}
		if (firstContestExecutionTime == secongContestExecutionTime)
			return 0;
		return firstContestExecutionTime > secongContestExecutionTime ? 1 : -1;
	}
}
