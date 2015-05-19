package com.nwchecker.server.service;


import com.nwchecker.server.json.ContestPassJson;

import java.util.List;

public interface RatingService {
    boolean scoreCalculateIfDynamicContest(int contestId);
    List<ContestPassJson> getJsonListForContestPassByContestId(int contestId);
}
