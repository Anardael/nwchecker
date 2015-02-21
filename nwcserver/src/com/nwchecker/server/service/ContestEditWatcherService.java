package com.nwchecker.server.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * Created by Роман on 21.02.2015.
 */
public interface ContestEditWatcherService {

    Map<Integer,String> getNowEditsMap();

    void addContestEditingUser(int contestId, String username, DeferredResult<String> deferredResult);

    void removeContestEditingUser(int contestId);

    void setDeferredResult(int requestId, String result);

    //check if contest is currently Editing (return String- username of editor):
    String isEditing(int contestId);

    void addRequestStillContestEditing(int contestId, DeferredResult<String> deferredResult);
}
