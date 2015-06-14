package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.service.CheckerService;
import com.nwchecker.server.service.TaskService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <h1>CheckerServiceImpl Test</h1> Test for CheckerServiceImpl method.
 * <p>
 * 
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-10
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/forTests/context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class CheckerServiceImplTest {
	@Autowired
	private CheckerService checkerService;
	@Autowired
	private TaskService taskService;
	
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testCheckTask() throws Exception {
		Task task = taskService.getTaskById(1);
		Map<String, Object> result = checkerService.checkTask(task, 1, new byte[8], new TaskPass());

		assertTrue(result.containsKey("results"));
		assertTrue(result.containsKey("successful"));
		assertTrue(result.containsKey("total"));
		assertEquals(1, result.get("total"));
		assertTrue(result.containsKey("passed"));
		 
		Map<String, Object> result2 = checkerService.checkTask(task, 1, new byte[8], new TaskPass());
		assertTrue(result2.containsKey("results"));
		assertTrue(result2.containsKey("successful"));
		assertTrue(result2.containsKey("total"));
		assertEquals(1, result.get("total"));
		assertTrue(result.containsKey("passed"));
		}
	}