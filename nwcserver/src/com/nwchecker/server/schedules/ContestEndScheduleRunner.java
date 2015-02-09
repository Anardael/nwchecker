package com.nwchecker.server.schedules;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * Created by Роман on 08.02.2015.
 */
public class ContestEndScheduleRunner extends TimerTask{

    private ContestService contestService;
    private Contest contest;
    private static final Logger LOG
            = Logger.getLogger(ContestStartScheduleRunner.class);

    public ContestEndScheduleRunner(ContestService contestService, Contest contest) {
        this.contestService = contestService;
        this.contest = contest;
    }

    @Override
    public void run() {
        contest.setStatus(Contest.Status.ARCHIEVE);
        contest.setHidden(true);
        contestService.updateContest(contest);
        LOG.info("Contest (id="+contest.getId()+") changed status to ARCHIEVE");
    }
}
