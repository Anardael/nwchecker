package com.nwchecker.server.service;


public interface PageTrackingService {
    void addTrack(String username, String sessionId, String viewName);

    String getViewByUsername(String username);

    String getSessionByUsername(String username);
}
