package com.nwchecker.server.handlers;

import com.nwchecker.server.service.PageTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PageTrackingHandler extends HandlerInterceptorAdapter {
    @Autowired
    private PageTrackingService pageTrackingService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(request.isUserInRole("ROLE_TEACHER")){
            //System.out.println("getRequestedSessionId(): " + request.getRequestedSessionId());
            System.out.println(request.getServletPath() + "   " + request.getRemoteUser());
            System.out.println();
            pageTrackingService.addTrack(request.getRemoteUser(), request.getServletPath());
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
