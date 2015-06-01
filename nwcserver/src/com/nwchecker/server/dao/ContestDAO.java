package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;

import java.util.List;

/**
 * <h1>Contest DAO</h1>
 * DAO for working with Contest Entity table.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public interface ContestDAO {

    /**
     * Add new Contest to database.
     * <p>
     *
     * @param c Contest that will be inserted in DB
     */
    void addContest(Contest c);

    /**
     * Update existing Contest in database.
     * <p>
     *
     * @param c Contest that will be updated in DB
     */
    void updateContest(Contest c);

    /**
     * Update existing Contest in database.
     *
     * @param c Contest that will be updated in DB
     */
    void mergeContest(Contest c);

    /**
     * Return all Contests from database.
     * <p>
     *
     * @return List of Contests from DB
     */
    List<Contest> getContests();

    /**
     * Return Contest from database by unique ID.
     * <p>
     *
     * @param id Unique ID of required Contest
     * @return Contest from DB
     */
    Contest getContestByID(int id);

    /**
     * Return all Contests that has specific status.
     * <p>
     * @param status Specific status of required Contests
     * @return List of Contests that have specific status
     */
    List<Contest> getContestByStatus(Contest.Status status);

	 public List<Contest> getPagedContests( int pageSize, int startIndex);
	
	 public Long getEntryCount();

	 public List<Contest> getPagedContests(Contest.Status status, int pageSize, int startIndex);
		
	 public Long getEntryCount(Contest.Status status);

    List<Contest> getContestsForRating();

    List<Contest> getContestsWithDynamicRating();

    public Contest getNearestContest() ;

    public Contest getLastArchivedContest();

    public Long getEntryCountForRating();
}
