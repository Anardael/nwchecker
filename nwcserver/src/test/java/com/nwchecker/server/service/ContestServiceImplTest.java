package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <h1>ContestServiceImpl Test</h1>
 * Test for ContestServiceImpl methods.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class ContestServiceImplTest {

    @Autowired
    private ContestService contestService;

    private List<Contest> contestList = new LinkedList<Contest>();
    private List<User> userList = new LinkedList<User>();

    @Before
    public void init() {
        for (int i = 0; i < 5; i++) {
            Contest c = new Contest();
            c.setId(i);
            c.setTitle("I am title " + i);
            c.setDescription("I am description " + i);
            contestList.add(c);
        }
    }


    @Test
    //@DatabaseSetup("classpath:/forTests/dataset.xml")
    @DatabaseTearDown(value = {"classpath:/forTests/dataset.xml"}, type = DatabaseOperation.DELETE_ALL)
    public void testAddContest() {
        contestService.addContest(contestList.get(0));
        Contest contest = contestService.getContestByID(contestList.get(0).getId());
        assertEquals(contest, contestList.get(0));
    }

    /**
     * Test of updateContest method, of class ContestServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testUpdateContest() {
        Contest contest = new Contest();
        contest.setId(1);
        contest.setTitle("I am new title");
        contestService.updateContest(contest);

        assertEquals(contest, contestService.getContestByID(contest.getId()));
    }

    /**
     * Test of mergeContest method, of class ContestServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testMergeContest() {
        Contest contest = contestService.getContestByID(1);

        //create new Contest and try it to merge:
        Contest newContest = new Contest();
        newContest.setId(contest.getId());
        newContest.setTitle("I am absolutely different contest title");
        newContest.setDescription("I am description");
        newContest.setTasks(contest.getTasks());

        contestService.mergeContest(newContest);
        assertEquals(newContest, contestService.getContestByID(contest.getId()));
    }

    /**
     * Test of getContests method, of class ContestServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetContests() {
        List<Contest> result = contestService.getContests();
        assertTrue(result.size() == 3);
    }

    /**
     * Test of getContestByID method, of class ContestServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetContestByID() {
        Contest expResult = contestList.get(2);
        Contest result = contestService.getContestByID(2);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfUserHavesAccessToContest method, of class
     * ContestServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testCheckIfUserHaveAccessToContest() {
        String username = "user";
        boolean result = contestService.checkIfUserHaveAccessToContest(username, 1);
        assertTrue(result);
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testFalseCheckIfUserHaveAccessToContest() {
        String username = "user";
        boolean result = contestService.checkIfUserHaveAccessToContest(username, 2);
        assertFalse(result);
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetContestForRating() {
        List<Contest> contests = contestService.getContestForRating();
        assertEquals(0, contests.size());
    }

}
