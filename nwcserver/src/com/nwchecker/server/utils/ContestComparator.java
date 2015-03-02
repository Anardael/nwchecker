package com.nwchecker.server.utils;

import com.nwchecker.server.model.Contest;

import java.util.Comparator;

/**
 * Created by Роман on 02.03.2015.
 */

//used for sorting contests with status "GOING" or "RELEASE" in excution time order.
public class ContestComparator implements Comparator<Contest> {
    @Override
    public int compare(Contest contest1, Contest contest2) {
        if (!(contest1.getStatus().equals(Contest.Status.RELEASE) || contest1.getStatus().equals(Contest.Status.GOING) &&
                (contest2.getStatus().equals(Contest.Status.RELEASE) || contest2.getStatus().equals(Contest.Status.GOING)))) {
            throw new IllegalArgumentException();
        }
        long firstContestExecutionTime = 0;
        long secongContestExecutionTime = 0;

        if (contest1.getStatus().equals(Contest.Status.RELEASE)) {
            //if release- execution time==start time:
            firstContestExecutionTime = contest1.getStarts().getTime();
        } else {
            //if status==GOING- execution time= start date + duration:
            firstContestExecutionTime = contest1.getStarts().getTime() + contest1.getDuration().getTime()+ 2 * 60 * 60 * 1000;
        }

        if (contest2.getStatus().equals(Contest.Status.RELEASE)) {
            secongContestExecutionTime = contest2.getStarts().getTime();
        } else {
            secongContestExecutionTime = contest2.getStarts().getTime() + contest2.getDuration().getTime() + 2 * 60 * 60 * 1000;
        }
        long result = firstContestExecutionTime - secongContestExecutionTime;
        return (int) result;
    }
}
