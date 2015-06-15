package com.nwchecker.server.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageTrackingHandler extends HandlerInterceptorAdapter implements PageTracking{
    private static Map<String, String> viewMap = Collections.synchronizedMap(new LinkedHashMap<String, String>());
    private static Map<String, String> sessionsMap = new ConcurrentHashMap<>();

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if(request.isUserInRole("ROLE_TEACHER") && modelAndView != null){
            viewMap.put(request.getRemoteUser(), modelAndView.getViewName());
            sessionsMap.put(request.getRemoteUser(), request.getSession().getId());
        }
        super.postHandle(request, response, handler, modelAndView);
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
