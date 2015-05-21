package com.nwchecker.server.dao;

import com.nwchecker.server.model.ContestPass;

import java.util.List;

/**
 * <h1>Contest Pass DAO</h1>
 * DAO for working with ContestPass Entity table.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-21
 */
public interface ContestPassDAO {

    /**
     * Add ContestPass in database
     * <p>
     *
     * @param contestPass ContestPass that will be inserted in DB
     */
    void saveContestPass(ContestPass contestPass);

    /**
     * Update existing ContestPass in database.
     *
     * @param contestPass ContestPass that will be updated in DB
     */
    void updateContestPass(ContestPass contestPass);

    /**
     * Return list of passed contests for specific Contest.
     * <p>
     *
     * @param contestId ID of specific Contest
     * @return List of passed contests for specific Contest
     */
    List<ContestPass> getContestPasses(int contestId);

    ContestPass getContestPassByUserIdAndContestId(int userId, int contestId);

}
