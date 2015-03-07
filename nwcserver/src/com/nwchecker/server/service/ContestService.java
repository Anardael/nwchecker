package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.model.Contest;

import java.util.List;

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
     * Set DAO
     *
     * @param dao Contest DAO
     */
    public void setContestDAO(ContestDAO dao);

    /**
     * Set User Service
     *
     * @param userService User Service
     */
    public void setUserService(UserService userService);

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

    /**
     * Checks if some User can edit some Contest.
     * <p>
     *
     * @param username Unique Username of existing User
     * @param ContestId Unique ID of existing User
     * @return <b>true</b> if User can edit Contest
     *         <b>false</b> if User can not edit Contest
     */
    public boolean checkIfUserHaveAccessToContest(String username, int ContestId);

}
