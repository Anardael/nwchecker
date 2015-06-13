package com.nwchecker.server.service;


import com.nwchecker.server.model.ContestPass;

import java.util.List;

public interface RatingService {
    List<ContestPass> getJsonListForContestPassByContestId(int contestId);
}
