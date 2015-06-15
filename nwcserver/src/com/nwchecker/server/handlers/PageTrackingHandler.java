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
        if(request.isUserInRole("ROLE_TEACHER") && modelAndView != null){
            pageTrackingService.addTrack(request.getRemoteUser(), modelAndView.getViewName(), request.getSession().getId());
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
