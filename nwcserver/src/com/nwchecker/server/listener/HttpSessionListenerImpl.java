package com.nwchecker.server.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class HttpSessionListenerImpl implements HttpSessionListener{
    private static final Map<String, HttpSession> sessions = new HashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
    }

    public static boolean sessionIsAliveById(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    public static void addSession(String sessionId, HttpSession httpSession){
        sessions.put(sessionId, httpSession);
    }
}
