package com.nwchecker.server.service;


import com.nwchecker.server.model.ContestPass;

import java.util.List;

public interface RatingService {
	/**
	 * Return list of Json-like objects ready to be displayed on the rating page, that represent Users' statistic in some contest.
	 * @param contestId ID of the contest.
	 * @return List of Json-like objects.
	 */
    List<ContestPass> getJsonListForContestPassByContestId(int contestId);
}
