package com.nwchecker.server.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service("PageTrackingService")
public class PageTrackingServiceImpl implements PageTrackingService {
    private static Map<String, String> trackerMap = new HashMap<>();

    @Override
    public void addTrack(String username, String servletPath) {
        trackerMap.put(username, servletPath);
    }

    @Override
    public void removeTrackByUsername(String userName) {
        trackerMap.remove(userName);
    }

    @Override
    public String getPathByUsername(String username){
        return trackerMap.get(username);
    }

    @Override
    public List<String> getUsernamesByPath(String servletPath) {
        List<String> userNames = null;
        if(trackerMap.containsValue(servletPath)){
            userNames = new ArrayList<>();
            Set<String> userNameSet = trackerMap.keySet();
            for (String key : userNameSet){
                if(trackerMap.get(key).equals(servletPath)) {
                    userNames.add(key);
                }
            }
        }
        return userNames;
    }

    @Override
    public boolean containPath(String servletPath) {
        return trackerMap.containsValue(servletPath);
    }
}
