package com.nwchecker.server.service;


import com.nwchecker.server.model.Contest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service("RatingService")
public class RatingServiceImpl implements RatingService {
    private static final Logger LOG = Logger.getLogger(RatingServiceImpl.class);

    @Autowired
    ScoreCalculationService scoreCalculationService;
    @Autowired
    ContestService contestService;

    @Override
    public boolean scoreCalculateIfDynamicContest(int contestId) {
        LOG.debug("Start method scoreCalculateIfDynamicContest. Param = {contestId: " + contestId + "}");
        Contest contest = contestService.getContestByID(contestId);

        if (contest.getTypeContest() != null && contest.getTypeContest().isDynamic()) {
            scoreCalculationService.calculateScore(contestId);
            LOG.debug("Finish method scoreCalculateIfDynamicContest. Return: " + true);
            return true;
        } else  {
            LOG.debug("Finish method scoreCalculateIfDynamicContest. Return: " + false);
            return false;
        }
    }
}
