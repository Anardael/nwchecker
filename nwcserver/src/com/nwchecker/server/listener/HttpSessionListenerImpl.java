package com.nwchecker.server.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final Map<String, HttpSession> sessions = new HashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
        /*System.out.println("SESSION CREATED: " + session.getId());
        System.out.println(sessions + "\n");*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
        /*System.out.println("SESSION DESTROYED: " + event.getSession().getId());
        System.out.println(sessions + "\n");*/
    }

    public static boolean sessionIsAliveById(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    public static Map<String, HttpSession> getSessions(){
        return sessions;
    }
}
