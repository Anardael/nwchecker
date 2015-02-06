/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestServiceImpl;
import com.nwchecker.server.service.UserService;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Роман
 */
@RunWith(MockitoJUnitRunner.class)
public class ContestServiceImplTest {

    private ContestDAO contestDAO;
    private UserService userService;
    private ContestServiceImpl contestService = new ContestServiceImpl();
    private List<Contest> contestList = new LinkedList<Contest>();
    private List<User> userList = new LinkedList<User>();

    @Before
    public void setUp() {
        contestDAO = mock(ContestDAO.class);
        contestService.setContestDAO(contestDAO);
        //user service:
        userService = mock(UserService.class);
        contestService.setUserService(userService);
        //create test Contest:
        Contest contest1 = new Contest();
        contest1.setId(11);
        contestList.add(contest1);
        //create teacher:
        User user1 = new User();
        user1.setUsername("TeacherUser1");
        user1.setContest(contestList);
        userList.add(user1);
        User user2 = new User();
        user2.setUsername("TeacherUser2");
        user2.setContest(contestList);
        userList.add(user2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addContest method, of class ContestServiceImpl.
     */
    @Test
    public void testAddContest() {
        System.out.println("addContest");
        Contest c = new Contest();
        contestService.addContest(c);
        verify(contestDAO, times(1)).addContest(c);
        verifyNoMoreInteractions(contestDAO);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateContest method, of class ContestServiceImpl.
     */
    @Test
    public void testUpdateContest() {
        System.out.println("updateContest");
        Contest c = new Contest();
        contestService.updateContest(c);
        verify(contestDAO, times(1)).updateContest(c);
        verifyNoMoreInteractions(contestDAO);
    }

    /**
     * Test of mergeContest method, of class ContestServiceImpl.
     */
    @Test
    public void testMergeContest() {
        System.out.println("mergeContest");
        Contest c = new Contest();
        contestService.mergeContest(c);
        verify(contestDAO, times(1)).mergeContest(c);
        verifyNoMoreInteractions(contestDAO);
    }

    /**
     * Test of getContests method, of class ContestServiceImpl.
     */
    @Test
    public void testGetContests() {
        System.out.println("getContests");
        when(contestDAO.getContests()).thenReturn(contestList);
        List<Contest> expResult = contestList;
        List<Contest> result = contestService.getContests();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getContestByID method, of class ContestServiceImpl.
     */
    @Test
    public void testGetContestByID() {
        System.out.println("getContestByID");
        int id = 11;
        when(contestDAO.getContestByID(id)).thenReturn(contestList.get(0));

        Contest expResult = contestList.get(0);
        Contest result = contestService.getContestByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of checkIfUserHavseAccessToContest method, of class
     * ContestServiceImpl.
     */
    @Test
    public void testCheckIfUserHaveAccessToContest() {
        System.out.println("checkIfUserHaveAccessToContest");
        when(userService.getUserByUsername("TeacherUser1")).thenReturn(userList.get(0));

        String username = "TeacherUser1";
        int ContestId = 11;
        boolean expResult = true;
        boolean result = contestService.checkIfUserHaveAccessToContest(username, ContestId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
