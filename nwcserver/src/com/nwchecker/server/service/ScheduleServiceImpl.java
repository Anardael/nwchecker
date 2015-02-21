package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.schedules.ContestEndScheduleRunner;
import com.nwchecker.server.schedules.ContestStartScheduleRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service(value = "ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ContestService contestService;

    private static final Logger LOG
            = Logger.getLogger(ScheduleServiceImpl.class);

    public void init() {
        //on initialization go throw all RELEASE contests and register for them couple of timers:
        List<Contest> releaseContests = contestService.getContestByStatus(Contest.Status.RELEASE);
        for (Contest contest : releaseContests) {
            registerStartContestSchedule(contest);
            registerEndContestSchedule(contest);
        }
        //for already started contests, register only END schedule:
        List<Contest> goingContests = contestService.getContestByStatus(Contest.Status.GOING);
        for (Contest contest : goingContests) {
            registerEndContestSchedule(contest);
        }
    }

    public void registerStartContestSchedule(Contest contest) {
        Timer t = new Timer();
        t.schedule(new ContestStartScheduleRunner(contestService, contest), contest.getStarts());

        LOG.info("Registered start schedule for contest id=" + contest.getId());
    }

    public void registerEndContestSchedule(Contest contest) {
        //get contest end date:
        long startDate = contest.getStarts().getTime();
        long duration = contest.getDuration().getTime() + 2 * 60 * 60 * 1000;
        long expiredTime = startDate + duration;
        Date expiredDate = new Date(expiredTime);

        Timer t = new Timer();
        t.schedule(new ContestEndScheduleRunner(contestService, contest), expiredDate);

        LOG.info("Registered stop schedule for contest id=" + contest.getId());
    }
}


