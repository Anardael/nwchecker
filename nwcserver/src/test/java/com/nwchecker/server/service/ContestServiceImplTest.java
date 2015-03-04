/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * @author Роман
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

public class ContestServiceImplTest {

    @Autowired
    private ContestDAO contestDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private ContestService contestService;

    @Autowired
    DataSource dataSource;

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
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    @DatabaseTearDown(value = {"classpath:/forTests/dataset.xml"}, type = DatabaseOperation.DELETE_ALL)
    public void testAddContest() {
        contestService.addContest(contestList.get(0));

        Contest contest = contestService.getContestByID(contestList.get(0).getId());
        assertEquals(contest,contestList.get(0));
    }

    /**
     * Test of updateContest method, of class ContestServiceImpl.
     */
    @Test
    @Ignore
    public void testUpdateContest() {
        Contest c = new Contest();
        contestService.updateContest(c);
    }

    /**
     * Test of mergeContest method, of class ContestServiceImpl.
     */
    @Test
    @Ignore
    public void testMergeContest() {
        Contest c = new Contest();
        contestService.mergeContest(c);
    }

    /**
     * Test of getContests method, of class ContestServiceImpl.
     */
    @Test
    @Ignore
    public void testGetContests() {
        List<Contest> expResult = contestList;
        List<Contest> result = contestService.getContests();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContestByID method, of class ContestServiceImpl.
     */
    @Test
    @Ignore
    public void testGetContestByID() {
        int id = 11;

        Contest expResult = contestList.get(0);
        Contest result = contestService.getContestByID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfUserHavesAccessToContest method, of class
     * ContestServiceImpl.
     */
    @Test
    @Ignore
    public void testCheckIfUserHaveAccessToContest() {

        String username = "TeacherUser1";
        int ContestId = 11;
        boolean expResult = true;
        boolean result = contestService.checkIfUserHaveAccessToContest(username, ContestId);
        assertEquals(expResult, result);
    }

}
