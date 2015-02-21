package com.nwchecker.server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Роман on 21.02.2015.
 */

@Service(value = "ContestEditWatcher")
public class ContestEditWatcherServiceImpl implements ContestEditWatcherService {

    //User editing long polling:
    private static Map<Integer, Map<String, Object>> CURRENTLY_EDITING_CONTESTS = new LinkedHashMap<>();
    //Requests for check if user is still editing contest
    private static Map<Integer, DeferredResult<String>> REQUEST_CONTEST_STILL_EDITING = new LinkedHashMap<>();

    @Override
    public void addRequestStillContestEditing(int contestId, DeferredResult<String> deferredResult) {
        REQUEST_CONTEST_STILL_EDITING.put(contestId, deferredResult);
    }

    @Override
    public Map<Integer, String> getNowEditsMap() {
        Map<Integer, String> result = new LinkedHashMap<>();
        for (Integer i : CURRENTLY_EDITING_CONTESTS.keySet()) {
             Map<String, Object> info = CURRENTLY_EDITING_CONTESTS.get(i);
            result.put(i, (String) info.get("username"));
        }
        return result;
    }

    @Override
    public void addContestEditingUser(int contestId, String username, DeferredResult<String> deferredResult) {
        Map<String, Object> requestinfo = new LinkedHashMap<>();
        requestinfo.put("username", username);
        requestinfo.put("deferredResult", deferredResult);
        CURRENTLY_EDITING_CONTESTS.put(contestId, requestinfo);
        //if somebody checks if contest is currently editing:
        if (REQUEST_CONTEST_STILL_EDITING.containsKey(contestId)) {
            REQUEST_CONTEST_STILL_EDITING.get(contestId).setResult(username);
        }

    }

    @Override
    public void removeContestEditingUser(int contestId) {
        CURRENTLY_EDITING_CONTESTS.remove(contestId);
    }

    @Override
    public void setDeferredResult(int requestId, String result) {
        if (CURRENTLY_EDITING_CONTESTS.containsKey(requestId)) {
            ((DeferredResult<String>) CURRENTLY_EDITING_CONTESTS.get(requestId).get("deferredResult")).setResult(result);
        }
    }

    @Override
    public String isEditing(int contestId) {
        if (CURRENTLY_EDITING_CONTESTS.containsKey(contestId)) {
            return (String) CURRENTLY_EDITING_CONTESTS.get(contestId).get("username");
        } else {
            return null;
        }
    }

}
