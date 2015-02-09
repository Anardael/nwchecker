package com.nwchecker.server.schedules;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * Created by Роман on 08.02.2015.
 */
public class ContestStartScheduleRunner extends TimerTask {

    private ContestService contestService;
    private Contest contest;
    private static final Logger LOG
            = Logger.getLogger(ContestStartScheduleRunner.class);

    public ContestStartScheduleRunner(ContestService service, Contest contest) {
        this.contestService = service;
        this.contest = contest;
    }

    @Override
    public void run() {
        contest.setStatus(Contest.Status.GOING);
        contestService.updateContest(contest);
        LOG.info("Contest (id="+contest.getId()+") changed status to GOING");
    }
}
