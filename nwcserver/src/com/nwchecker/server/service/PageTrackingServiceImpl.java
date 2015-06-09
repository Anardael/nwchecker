package com.nwchecker.server.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service("PageTrackingService")
public class PageTrackingServiceImpl implements PageTrackingService {
    private static Map<String, String> trackerMap = new LinkedHashMap<>();
    private static Map<String, String> sessionsMap = new HashMap<>();

    @Override
    public void addTrack(String username, String servletPath, String sessionId) {
        trackerMap.put(username, servletPath);
        sessionsMap.put(username, sessionId);
    }

    @Override
    public String getPathByUsername(String username){
        return trackerMap.get(username);
    }

    @Override
    public String getSessionByUsername(String username){
        return sessionsMap.get(username);
    }
}
