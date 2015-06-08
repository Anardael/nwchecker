package com.nwchecker.server.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Set;

public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final Set<String> sessions = new HashSet<>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.add(session.getId());
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
    }

    public static boolean sessionIsAliveById(String sessionId) {
        return sessions.contains(sessionId);
    }
}
