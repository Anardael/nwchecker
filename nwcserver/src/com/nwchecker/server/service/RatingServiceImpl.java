package com.nwchecker.server.service;


import com.nwchecker.server.model.ContestPass;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RatingService")
public class RatingServiceImpl implements RatingService {
    private static final Logger LOG = Logger.getLogger(RatingServiceImpl.class);

    @Autowired
    private ScoreCalculationService scoreCalculationService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ContestPassService contestPassService;


    @Override
    public List<ContestPass> getJsonListForContestPassByContestId(int contestId) {
    	LOG.info("Retrieved list of contests");
        List<ContestPass> contestPasses = contestPassService.getValidContestPasses(contestId);        
        return contestPasses;
    }
}
