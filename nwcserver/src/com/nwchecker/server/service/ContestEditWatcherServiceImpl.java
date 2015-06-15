package com.nwchecker.server.service;

import com.nwchecker.server.handlers.PageTracking;
import com.nwchecker.server.listener.HttpSessionListenerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service(value = "ContestEditWatcher")
public class ContestEditWatcherServiceImpl implements ContestEditWatcherService {
    private static Map<Integer, String> lastEditorInContest = new ConcurrentHashMap<>();
    private static Map<String, Integer> lastEditedContestByUser = new ConcurrentHashMap<>();

    private final static Set<String> EDIT_VIEW_SET = new HashSet<>();

    static {
        EDIT_VIEW_SET.add("nwcserver.contests.create");
        EDIT_VIEW_SET.add("fragments/createNewTaskForm");
        EDIT_VIEW_SET.add("fragments/taskDataView");
    }

    @Autowired
    private PageTracking pageTracking;

    @Override
    public void add(int contestId, String username){
        lastEditorInContest.put(contestId, username);
        lastEditedContestByUser.put(username, contestId);
    }

    @Override
    public boolean checkContestIsEditedById(int contestId, String currentUsername){
        String lastEditorUsername = lastEditorInContest.get(contestId);
        String lastUserView = pageTracking.getViewByUsername(lastEditorUsername);
        String userSessionId = pageTracking.getSessionByUsername(lastEditorUsername);

        return (EDIT_VIEW_SET.contains(lastUserView)
                && HttpSessionListenerImpl.sessionIsAliveById(userSessionId)
                && lastEditedContestByUser.get(lastEditorUsername)!= null
                && lastEditedContestByUser.get(lastEditorUsername)==contestId
                && !lastEditorUsername.equals(currentUsername))
                ? true : false;
    }

    @Override
    public String getLastContestEditorById(int contestId){
        return lastEditorInContest.get(contestId);
    }
}
