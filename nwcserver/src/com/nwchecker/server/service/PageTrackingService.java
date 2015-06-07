package com.nwchecker.server.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PageTrackingService {
    void addTrack(String username, String servletPath);

    String getPathByUsername(String username);

    void removeTrackByUsername(String username);

    List<String> getUsernamesByPath(String servletPath);

    boolean containPath(String servletPath);
}
