package com.nwchecker.server.service;


public interface PageTrackingService {
    void addTrack(String username, String viewName, String sessionId);

    String getViewByUsername(String username);

    String getSessionByUsername(String username);
}
