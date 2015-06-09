package com.nwchecker.server.handlers;

import com.nwchecker.server.service.PageTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageTrackingHandler extends HandlerInterceptorAdapter {
    @Autowired
    private PageTrackingService pageTrackingService;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        /*System.out.println(request.getRemoteUser() + " "
                + request.getServletPath() + " "
                + request.getSession().getId() + " "
                + request.getRequestedSessionId()
                + "\n");*/
        if(request.isUserInRole("ROLE_TEACHER")){
            //System.out.println(request.getRemoteUser() + " " + request.getServletPath() + " " + request.getSession().getId() + "\n");
            pageTrackingService.addTrack(request.getRemoteUser(), request.getServletPath(), request.getRequestedSessionId());
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
