package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.nwchecker.server.service.ContestEditWatcherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.context.request.async.DeferredResult;

import static org.junit.Assert.assertEquals;

/**
 * <h1>ContestEditWatcherServiceImpl Test</h1>
 * Test for ContestEditWatcherServiceImpl methods.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ContestEditWatcherServiceImplTest {
    private static final long CONTEST_EDIT_TIME_OUT_POLLING = 10000;

    final DeferredResult<String> deferredResult = new DeferredResult<>(CONTEST_EDIT_TIME_OUT_POLLING, "timeOut");

    final int contestId = 1;

    @Autowired
    private ContestEditWatcherService contestEditWatcherService;

    /**
     * Test of getNowEditsMap method, of class ContestEditWatcherServiceImpl.
     */
    @Test
    public void testGetNowEditsMap() throws Exception {
        contestEditWatcherService.addContestEditingUser(contestId, "Teacher", deferredResult);
        assertEquals(contestEditWatcherService.getNowEditsMap().size(), 1);
        contestEditWatcherService.removeContestEditingUser(contestId);
    }

    /**
     * Test of dddContestEditingUser method, of class ContestEditWatcherServiceImpl.
     */
    @Test
    public void testAddContestEditingUser() throws Exception {
        assertEquals(contestEditWatcherService.isEditing(contestId), null);
        contestEditWatcherService.addContestEditingUser(contestId, "Teacher", deferredResult);
        assertEquals(contestEditWatcherService.isEditing(contestId), "Teacher");
        contestEditWatcherService.removeContestEditingUser(contestId);
    }

    /**
     * Test of removeContestEditingUser method, of class ContestEditWatcherServiceImpl.
     */
    @Test
    public void testRemoveContestEditingUser() throws Exception {
        contestEditWatcherService.addContestEditingUser(contestId, "Teacher", deferredResult);
        assertEquals(contestEditWatcherService.isEditing(contestId), "Teacher");
        contestEditWatcherService.removeContestEditingUser(contestId);
        assertEquals(contestEditWatcherService.isEditing(contestId), null);
    }

    /**
     * Test of removeContestEditingUser method, of class ContestEditWatcherServiceImpl.
     */
    @Test
    public void testIsEditing() throws Exception {
        contestEditWatcherService.addContestEditingUser(contestId, "Teacher", deferredResult);
        assertEquals(contestEditWatcherService.isEditing(contestId), "Teacher");
        contestEditWatcherService.removeContestEditingUser(contestId);
    }
}