/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.nwchecker.server.service;

import com.nwchecker.server.dao.TaskDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.service.TaskServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Роман
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    private TaskDAO taskDAO;
    private TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
    private List<Task> taskList = new LinkedList<Task>();
    private TaskData taskData = new TaskData();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        taskDAO = mock(TaskDAO.class);
        //set dao to service:
        taskServiceImpl.setDAO(taskDAO);
        //create contest for Contest below:
        Contest contest = new Contest();
        contest.setId(11);
        contest.setTitle("Hello, I am Contest ;)");
        contest.setTasks(taskList);
        //TaskData:
        taskData.setId(41);
        //create few tasks:
        Task task1 = new Task();
        task1.setId(21);
        task1.setTitle("I am title ;)");
        task1.setComplexity(31);
        task1.setContest(contest);
        taskList.add(task1);
        //2:
        Task task2 = new Task();
        task2.setId(22);
        task2.setTitle("I am title ;)");
        task2.setComplexity(32);
        task2.setContest(contest);
        taskList.add(task2);
        //3:
        Task task3 = new Task();
        task3.setId(23);
        task3.setTitle("I am title ;)");
        task3.setComplexity(33);
        task3.setContest(contest);
        taskList.add(task3);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTaskById method, of class TaskServiceImpl.
     */
    @Test
    public void testGetTaskById() {
        System.out.println("getTaskById");

        when(taskDAO.getTaskById(21)).thenReturn(taskList.get(0));

        Task expResult = taskList.get(0);
        Task result = taskServiceImpl.getTaskById(21);

        assertEquals(expResult, result);
    }

    /**
     * Test of getTasksByContestId method, of class TaskServiceImpl.
     */
    @Test
    public void testGetTasksByContestId() {
        System.out.println("getTasksByContestId");
        int contestId = 11;

        when(taskDAO.getTasksByContestId(contestId)).thenReturn(taskList);
        List<Task> expResult = taskList;

        List<Task> result = taskServiceImpl.getTasksByContestId(contestId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTasks method, of class TaskServiceImpl.
     */
    @Test
    public void testGetTasks() {
        System.out.println("getTasks");

        when(taskDAO.getTasks()).thenReturn(taskList);
        List<Task> expResult = taskList;

        List<Task> result = taskServiceImpl.getTasks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addTask method, of class TaskServiceImpl.
     */
    @Test
    public void testAddTask() {
        System.out.println("addTask");
        Task t = taskList.get(0);
        taskServiceImpl.addTask(t);
        verify(taskDAO, times(1)).addTask(t);
        verifyNoMoreInteractions(taskDAO);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateTask method, of class TaskServiceImpl.
     */
    @Test
    public void testUpdateTask() {
        System.out.println("updateTask");
        Task t = taskList.get(0);
        taskServiceImpl.updateTask(t);
        verify(taskDAO, times(1)).updateTask(t);
        verifyNoMoreInteractions(taskDAO);
    }

    /**
     * Test of deleteTaskById method, of class TaskServiceImpl.
     */
    @Test
    public void testDeleteTaskById() {
        System.out.println("deleteTaskById");
        taskServiceImpl.deleteTaskById(0);
        verify(taskDAO, times(1)).deleteTaskById(0);
        verifyNoMoreInteractions(taskDAO);
    }

    /**
     * Test of getTaskData method, of class TaskServiceImpl.
     */
    @Test
    public void testGetTaskData() {
        System.out.println("getTaskData");
        when(taskDAO.getTaskData(0)).thenReturn(taskData);

        TaskData result = taskServiceImpl.getTaskData(0);
        assertEquals(taskData, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of deleteTaskData method, of class TaskServiceImpl.
     */
    @Test
    public void testDeleteTaskData() {
        System.out.println("deleteTaskData");
        taskServiceImpl.deleteTaskData(0);
        verify(taskDAO, times(1)).deleteTaskData(0);
        verifyNoMoreInteractions(taskDAO);
    }

}
