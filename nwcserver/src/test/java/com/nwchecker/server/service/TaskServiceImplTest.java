package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.utils.PaginationWrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * <h1>TaskServiceImpl Test</h1>
 * Test for TaskServiceImpl methods.
 * <p/>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    /**
     * Test of getTaskById method, of class TaskServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetTaskById() {
        assertEquals(taskService.getTaskById(1).getTitle(), "Task 1");
    }

    /**
     * Test of getTasksByContestId method, of class TaskServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetTasksByContestId() {
        int contestId = 2;
        List<Task> result = taskService.getTasksByContestId(contestId);
        assertEquals(result.get(0).getTitle(), "Task 3");
    }

    /**
     * Test of getTasks method, of class TaskServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetTasks() {
        assertEquals(taskService.getTasks().size(), 3);
    }

	/**
	 * Test of addTask method, of class TaskServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testAddTask() {
		Task task = new Task();
		task.setTitle("I am title");
		task.setComplexity(5);
		task.setContest(taskService.getTaskById(3).getContest());
		task.setDescription("this is a description.");
		task.setOutputFileName("output");
		task.setInputFileName("input");
		taskService.addTask(task);
		assertEquals(taskService.getTasksByContestId(2).size(), 2);
	}

    /**
     * Test of updateTask method, of class TaskServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testUpdateTask() {
        Task task = taskService.getTaskById(1);
        task.setTitle("Task1Updated");
        taskService.updateTask(task);
        assertEquals(taskService.getTaskById(1).getTitle(), "Task1Updated");
    }

    /**
     * Test of deleteTaskById method, of class TaskServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testDeleteTaskById() {
        assertEquals(taskService.getTasks().size(), 3);
        taskService.deleteTaskById(3);
        assertEquals(taskService.getTasks().size(), 2);
    }

    /**
     * Test of getTaskData method, of class TaskServiceImpl.
     */
    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetTaskData() {
        assertEquals(taskService.getTaskData(1).getTask().getTitle(), "Task 1");
    }

	/**
	 * Test of deleteTaskData method, of class TaskServiceImpl.
	 */
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testDeleteTaskData() {
		assertEquals(taskService.getTaskData(1).getTask().getTitle(), "Task 1");
		taskService.deleteTaskData(1);
		assertEquals(taskService.getTaskData(1), null);
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetTasksByContestStatus() {
		assertEquals(3,	taskService.getTasksByContestStatus(Contest.Status.PREPARING)
						.size());
	}

    @Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetPagedTasksByContestStatus() {
		assertEquals(0,	taskService.getPagedTasksByContestStatus(Contest.Status.GOING,
						0, 0, null, null, null).size());
	}

    
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetRecordCount() {
		assertEquals(0,	taskService.getRecordCount(Contest.Status.ARCHIVE, null).intValue());
	}
	
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetTaskWrapperForPagination(){
		PaginationWrapper<Task> paginatedTasks = taskService.getTaskWrapperForPagination(Contest.Status.PREPARING, 0, 10, null, null, "1");
		assertEquals(paginatedTasks.getDataList().size(), paginatedTasks.getRecordCount().intValue());
	}
}
