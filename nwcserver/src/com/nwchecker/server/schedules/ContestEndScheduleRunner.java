package com.nwchecker.server.schedules;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.ScoreCalculationService;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * Created by Роман on 08.02.2015.
 */
public class ContestEndScheduleRunner extends TimerTask {

    private ContestService contestService;
    private Contest contest;
    private ScoreCalculationService scoreCalculationService;
    private static final Logger LOG
            = Logger.getLogger(ContestStartScheduleRunner.class);

    public ContestEndScheduleRunner(ContestService contestService, ScoreCalculationService scoreCalculationService, Contest contest) {
        this.scoreCalculationService = scoreCalculationService;
        this.contestService = contestService;
        this.contest = contest;
    }

    @Override
    public void run() {
        contest.setStatus(Contest.Status.ARCHIVE);
        contest.setHidden(true);
        contestService.updateContest(contest);
        scoreCalculationService.calculateScore(contest.getId());
        LOG.info("Contest (id=" + contest.getId() + ") changed status to ARCHIVE");
    }
}
