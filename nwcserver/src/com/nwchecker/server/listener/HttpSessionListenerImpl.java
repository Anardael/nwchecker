package com.nwchecker.server.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpSessionListenerImpl implements HttpSessionListener{
    private static final Map<String, HttpSession> sessions = Collections.synchronizedMap(new HashMap<String, HttpSession>());

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

    public static boolean checkSessionActivityByMinutes(String sessionId, long timeOutMinutes){
        if(sessionId == null){
            return false;
        }
        HttpSession session = sessions.get(sessionId);
        long minutesToLastActivity = new Date().getTime() - session.getLastAccessedTime();

        if(minutesToLastActivity < timeOutMinutes * 60 * 1000){
            return true;
        } else {
            session.invalidate();
            return false;
        }
    }

    public static void addSession(String sessionId, HttpSession httpSession){
        sessions.put(sessionId, httpSession);
    }
}
