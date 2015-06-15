package com.nwchecker.server.service;


public interface ContestEditWatcherService {
    void add(int contestId, String username);

    boolean checkContestIsEditedById(int contestId, String currentUsername);

    String getLastContestEditorById(int contestId);
}
