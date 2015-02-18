package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.schedules.ContestEndScheduleRunner;
import com.nwchecker.server.schedules.ContestStartScheduleRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;

@Service(value = "scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ContestService contestService;

    public void registerStartContestSchedule(Contest contest) {
        Timer t = new Timer();
        t.schedule(new ContestStartScheduleRunner(contestService, contest), contest.getStarts());
    }

    public void registerEndContestSchedule(Contest contest) {
        Timer t = new Timer();
        //get contest end date:
        long startDate = contest.getStarts().getTime();
        long duration = contest.getDuration().getTime();
        duration += 7200000;
        long expiredTime = startDate + duration;
        Date expiredDate = new Date(expiredTime);
        t.schedule(new ContestEndScheduleRunner(contestService, contest), expiredDate);
    }
}


