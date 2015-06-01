package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.utils.PaginationWrapper;

import java.util.List;
import java.util.Map;

/**
 * <h1>Contest Service</h1>
 * Service that can save, update and delete Contests.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public interface ContestService {

    /**
     * Add new Contest to database.
     * <p>
     *
     * @param c Contest that will be inserted in DB
     */
    public void addContest(Contest c);

    /**
     * Update existing Contest in database.
     * <p>
     *
     * @param c Contest that will be updated in DB
     */
    public void updateContest(Contest c);

    /**
     * Update existing Contest in database.
     *
     * @param c Contest that will be updated in DB
     */
    public void mergeContest(Contest c);

    /**
     * Return all Contests from database.
     * <p>
     *
     * @return List of Contests from DB
     */
    public List<Contest> getContests();

    /**
     * Return Contest from database by unique ID.
     * <p>
     *
     * @param id Unique ID of required Contest
     * @return Contest from DB
     */
    public Contest getContestByID(int id);

    /**
     * Return all Contests that has specific status.
     * <p>
     * @param status Specific status of required Contests
     * @return List of Contests that have specific status
     */
    public List<Contest> getContestByStatus(Contest.Status status);

    List<Contest> getContestForRating();

    public boolean checkIfUserHaveAccessToContest(String username, int ContestId);
    
    public List<Contest> getPagedContests(int pageSize, int pageNumber);
    
    public List<Contest> getPagedContests(Contest.Status status, int pageSize, int pageNumber);
    
    public Long getPageCount(int pageSize);
    
    public Long getPageCount(Contest.Status status, int pageSize);

    Long getContestEndTime(Contest contest);

    public Long getEntryCountForRating();
}
