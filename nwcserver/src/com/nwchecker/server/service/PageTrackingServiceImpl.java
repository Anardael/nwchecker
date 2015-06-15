package com.nwchecker.server.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service("PageTrackingService")
public class PageTrackingServiceImpl implements PageTrackingService {
    private static Map<String, String> viewMap = new LinkedHashMap<>();
    private static Map<String, String> sessionsMap = new HashMap<>();

    @Override
    public void addTrack(String username, String viewName, String sessionId) {
        viewMap.put(username, viewName);
        sessionsMap.put(username, sessionId);
    }

    @Override
    public String getViewByUsername(String username){
        return viewMap.get(username);
    }

    @Override
    public String getSessionByUsername(String username){
        return sessionsMap.get(username);
    }
}
