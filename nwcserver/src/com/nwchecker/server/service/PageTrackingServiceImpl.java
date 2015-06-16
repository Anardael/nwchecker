package com.nwchecker.server.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service("PageTrackingService")
public class PageTrackingServiceImpl implements PageTrackingService {
    private static Map<String, String> viewMap = Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String, String> sessionsMap = Collections.synchronizedMap(new HashMap<String, String>());

    @Override
    public void addTrack(String username, String sessionId, String viewName){
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
