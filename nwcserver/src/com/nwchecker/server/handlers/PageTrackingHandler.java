package com.nwchecker.server.handlers;

import com.nwchecker.server.service.PageTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
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
        /*for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getName() + " -> " + cookie.getValue());
        }*/

        if(request.isUserInRole("ROLE_TEACHER") && modelAndView != null){
            System.out.println(request.getRemoteUser() + " " + request.getRequestedSessionId() + " " + modelAndView.getViewName());
            pageTrackingService.addTrack(request.getRemoteUser(), modelAndView.getViewName(), request.getRequestedSessionId());
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
