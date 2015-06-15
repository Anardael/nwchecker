package com.nwchecker.server.handlers;


import org.springframework.stereotype.Component;

@Component("PageTracking")
public interface PageTracking {
    String getViewByUsername(String username);

    String getSessionByUsername(String username);
}
