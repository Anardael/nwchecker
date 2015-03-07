package com.nwchecker.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerService implements ApplicationListener<org.springframework.context.event.ContextRefreshedEvent> {

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getDisplayName().contains("dispatcher")) {
            return;
        }
        scheduleService.refresh();
    }
}
