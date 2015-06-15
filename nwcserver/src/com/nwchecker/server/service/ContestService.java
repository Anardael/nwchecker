package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;

import java.util.List;

/**
 * <h1>Contest Service</h1> Service that can save, update and delete Contests.
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
	 * @param c
	 *            Contest that will be inserted in DB
	 */
	public void addContest(Contest c);

	/**
	 * Update existing Contest in database.
	 * <p>
	 *
	 * @param c
	 *            Contest that will be updated in DB
	 */
	public void updateContest(Contest c);

	/**
	 * Update existing Contest in database.
	 *
	 * @param c
	 *            Contest that will be updated in DB
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
	 * @param id
	 *            Unique ID of required Contest
	 * @return Contest from DB
	 */
	public Contest getContestByID(int id);

	List<Contest> getContestForRating();
	/**
	 * Check if User has access to contest.
	 * @param username Username of the user.
	 * @param ContestId 
	 * @return boolean value that represents if user has access to Contest.
	 */

	boolean checkIfUserHaveAccessToContest(String username, int ContestId);
	/**
	 * Return Contest's End time taking Time Zone difference into account.
	 * @param contest Contest
	 * @return Long that represents time difference between contest's end time and Epoch.
	 */

	Long getContestEndTime(Contest contest);

	/**
	 * Return next planned contest with status RELEASE.
	 * 
	 * @return Next planned Contest.
	 */
	Contest getNearestContest();

	/**
	 * Return last archived contest.
	 * 
	 * @return Last archived contest.
	 */

	Contest getLastArchivedContest();
	
	List<Contest> getContestsListByHiddenStatusUsername(String stringHidden,
			String stringStatus, String username);

	List<Contest> getUnhiddenContestsListByStatus(String stringStatus);
}
