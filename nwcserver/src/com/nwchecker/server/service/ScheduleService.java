package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;

/**
 * Created by Роман on 11.02.2015.
 */
public interface ScheduleService {
    void registerStartContestSchedule(Contest contest);

    void registerEndContestSchedule(Contest contest);
}
