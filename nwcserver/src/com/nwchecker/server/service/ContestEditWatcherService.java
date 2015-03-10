package com.nwchecker.server.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * <h1>Contest Edit Watcher Service</h1>
 * Service that can change list of Users that can edit
 * some Contest.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-21
 */
public interface ContestEditWatcherService {

    /**
     * Return Users that currently editing some Contest.
     * <p>
     *
     * @return Users that editing Contest
     */
    Map<Integer, String> getNowEditsMap();

    /**
     * Add User to List of Users that editing some Contest.
     * <p>
     *
     * @param contestId Unique ID of existing Contest
     * @param username Unique Username of existing User
     * @param deferredResult using for asynchronous response to user.
     */
    void addContestEditingUser(int contestId, String username, DeferredResult<String> deferredResult);

    /**
     * Remove User from Contest editing List.
     * <p>
     *
     * @param contestId Unique Id of exiting Contest
     */
    void removeContestEditingUser(int contestId);

    /**
     * set asynchronous response to user.
     * <p>
     *
     * @param requestId ID of Request
     * @param result is returning String to user.
     */
    void setDeferredResult(int requestId, String result);

    /**
     * Checks if Contest is currently Editing by someone else.
     * <p>
     *
     * @param contestId Unique ID of existing Contest
     * @return <b>true</b> Contest currently editing
     *         <b>false</b> Contest is free
     */
    String isEditing(int contestId);

    /**
     * Add Request that tells system that some User
     * still editing some Contest.
     * <p>
     *
     * @param contestId Unique ID of existing Contest
     * @param deferredResult set asynchronous response to user.
     */
    void addRequestStillContestEditing(int contestId, DeferredResult<String> deferredResult);

    /**
     * Tells system that Contest is free.
     * <p>
     *
     * @param contestId Unique ID of existing Contest
     */
    void removeRequestStillContestEditing(int contestId);
}
