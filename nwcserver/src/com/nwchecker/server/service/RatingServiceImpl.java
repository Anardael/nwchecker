package com.nwchecker.server.service;


import com.nwchecker.server.json.ContestPassJson;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("RatingService")
public class RatingServiceImpl implements RatingService {
    private static final Logger LOG = Logger.getLogger(RatingServiceImpl.class);

    @Autowired
    ScoreCalculationService scoreCalculationService;
    @Autowired
    ContestService contestService;
    @Autowired
    private ContestPassService contestPassService;

    @Override
    public boolean scoreCalculateIfDynamicContest(int contestId) {
        LOG.debug("Start method scoreCalculateIfDynamicContest. Param = {contestId: " + contestId + "}");
        Contest contest = contestService.getContestByID(contestId);

        if (contest.getTypeContest().isDynamic()) {
            scoreCalculationService.calculateScore(contestId);
            LOG.debug("Finish method scoreCalculateIfDynamicContest. Return: " + true);
            return true;
        } else  {
            LOG.debug("Finish method scoreCalculateIfDynamicContest. Return: " + false);
            return false;
        }
    }

    @Override
    public List<ContestPassJson> getJsonListForContestPassByContestId(int contestId) {
        List<ContestPass> contestPasses = contestPassService.getContestPasses(contestId);
        Collections.sort(contestPasses);
        List<ContestPassJson> jsonData = new ArrayList<>();
        for (ContestPass contestPass : contestPasses) {
            jsonData.add(ContestPassJson.createContestPassJson(contestPass));
        }
        return jsonData;
    }
}
