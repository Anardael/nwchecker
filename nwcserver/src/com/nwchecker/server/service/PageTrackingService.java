package com.nwchecker.server.service;

import java.util.List;


public interface PageTrackingService {
    void addTrack(String username, String servletPath);

    String getPathByUsername(String username);

    List<String> getUsernamesByPath(String servletPath);

    boolean containPath(String servletPath);
}
