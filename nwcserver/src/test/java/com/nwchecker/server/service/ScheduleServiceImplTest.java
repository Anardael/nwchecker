package test.java.com.nwchecker.server.service;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * <h1>ScheduleServiceImpl Test</h1>
 * Test for ScheduleServiceImpl methods.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class ScheduleServiceImplTest {

    @Autowired
    private ContestService contestService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Test of refresh method, of class ScheduleServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testRefresh() throws Exception {
        Contest contest = contestService.getContestByID(1);
        contest.setStarts(new Date());
        contest.setStatus(Contest.Status.RELEASE);
        contestService.mergeContest(contest);
        scheduleService.refresh();
        //duration contest = 5 seconds.
/*        try {
            Thread.sleep(3 * 1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertEquals(contestService.getContestByID(1).getStatus(), Contest.Status.GOING);*/
        try {
            Thread.sleep(3 * 1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertEquals(contestService.getContestByID(1).getStatus(), Contest.Status.ARCHIVE);
    }
}