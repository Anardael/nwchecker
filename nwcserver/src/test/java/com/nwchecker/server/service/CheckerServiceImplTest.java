package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.CheckerService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * <h1>CheckerServiceImpl Test</h1> Test for CheckerServiceImpl method.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/forTests/context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class CheckerServiceImplTest {

	@Mock
	private CheckerService checkerService;
	
	@Before
	public void init() {
		checkerService = mock(CheckerService.class);
		
		Map<String, Object> result1 = new HashMap<String, Object>();
		result1.put("passed", true);
		result1.put("time", 1000);
		result1.put("memory", 1000);
		
		Map<String, Object> result2 = new HashMap<String, Object>();
		result2.put("passed", false);
		result2.put("time", 3000);
		result2.put("memory", 3000);
		result2.put("message", "");
		
		when(checkerService.checkTask(any(Task.class), any(byte[].class), anyInt())).thenReturn(result1).thenReturn(result2);
	}

	@Test
	public void testCheckTask() throws Exception {
		Map<String, Object> result = checkerService.checkTask(new Task(),
				new byte[8], 0);

		assertTrue(result.containsKey("passed"));
		assertTrue(result.containsKey("time"));
		assertTrue(result.containsKey("memory"));

		if (!(boolean) result.get("passed")) {
			assertTrue(result.containsKey("message"));
		}
		Map<String, Object> result2 = checkerService.checkTask(new Task(),
				new byte[8], 0);
		assertTrue(result2.containsKey("passed"));
		assertTrue(result2.containsKey("time"));
		assertTrue(result2.containsKey("memory"));

		if (!(boolean) result2.get("passed")) {
			assertTrue(result2.containsKey("message"));
		}
	}
}