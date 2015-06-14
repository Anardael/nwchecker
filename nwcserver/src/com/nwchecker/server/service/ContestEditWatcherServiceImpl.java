package com.nwchecker.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service(value = "ContestEditWatcher")
public class ContestEditWatcherServiceImpl implements ContestEditWatcherService {
    private static Map<Integer, String> lastEditorInContest = new HashMap<>();
    private static Map<String, Integer> lastEditedContestByUser = new HashMap<>();

    private final static Set<String> EDIT_VIEW_SET = new HashSet<>();

    static {
        EDIT_VIEW_SET.add("nwcserver.contests.create");
        EDIT_VIEW_SET.add("fragments/createNewTaskForm");
        EDIT_VIEW_SET.add("fragments/taskDataView");
    }

    @Autowired
    private PageTrackingService pageTrackingService;

    @Override
    public void addParameters(int contestId, String username){
        lastEditorInContest.put(contestId, username);
        lastEditedContestByUser.put(username, contestId);
    }

    @Override
    public boolean checkContestIsEditedById(int contestId, String currentUsername){
        String lastEditorUsername = lastEditorInContest.get(contestId);
        String lastUserView = pageTrackingService.getPathByUsername(lastEditorUsername);
        String userSessionId = pageTrackingService.getSessionByUsername(lastEditorUsername);

        /*System.out.println("Map: ");
        System.out.println(HttpSessionListenerImpl.getSessions());
        System.out.println("UserSessionId: " + userSessionId);*/

        return (EDIT_VIEW_SET.contains(lastUserView)
                && lastEditedContestByUser.get(lastEditorUsername)!= null
                && lastEditedContestByUser.get(lastEditorUsername)==contestId
                && !lastEditorUsername.equals(currentUsername))
                /*&& HttpSessionListenerImpl.sessionIsAliveById(userSessionId)*/
                ? true : false;
    }

    @Override
    public String getLastContestEditorById(int contestId){
        return lastEditorInContest.get(contestId);
    }
}
