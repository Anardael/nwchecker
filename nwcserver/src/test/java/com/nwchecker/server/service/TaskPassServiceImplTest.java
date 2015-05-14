package test.java.com.nwchecker.server.service;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.json.TaskPassJson;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.service.TaskPassService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/forTests/context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TaskPassServiceImplTest {

	@Autowired
	TaskPassService taskPassService;

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetTaskPassSampleSize() {
		Long sample1 = taskPassService.getTaskPassSampleSize(1);
		assertEquals(6L, sample1.longValue());
		Long sample2 = taskPassService.getTaskPassSampleSize(2);
		assertEquals(2L, sample2.longValue());
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetSuccessfulTaskPassSampleSize() {
		Long sample1 = taskPassService.getSuccessfulTaskPassSampleSize(1);
		assertEquals(2L, sample1.longValue());
		Long sample2 = taskPassService.getSuccessfulTaskPassSampleSize(2);
		assertEquals(2L, sample2.longValue());
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetPagedTaskPassesForTask() {
		List<TaskPassJson> taskPasses = taskPassService
				.getPagedTaskPassesForTask(2, 0, 5);
		assertEquals(2, taskPasses.size());
		List<TaskPassJson> taskPasses2 = taskPassService
				.getPagedTaskPassesForTask(1, 0, 5);
		assertEquals(5, taskPasses2.size());
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetPagedTaskPassesForTaskSorted() {
		List<TaskPassJson> taskPasses2 = taskPassService
				.getPagedTaskPassesForTask(1, 0, 6);
		Collections.sort(taskPasses2, new Comparator<TaskPassJson>() {
			@Override
			public int compare(TaskPassJson current, TaskPassJson other) {
				return current.getExecutionTime() > other.getExecutionTime() ? 1
						: current.getExecutionTime() == other
								.getExecutionTime() ? 0 : -1;
			}
		});
		List<TaskPassJson> taskPassesSorted = taskPassService.getPagedTaskPassesForTask(1, 0, 6, "executionTime asc");
		Iterator<TaskPassJson> controlled = taskPasses2.iterator();
		Iterator<TaskPassJson> testing = taskPassesSorted.iterator();
		while(controlled.hasNext() && testing.hasNext()){
			TaskPassJson expected = controlled.next();
			TaskPassJson actual = testing.next();
			assertEquals((long) expected.getExecutionTime(),(long) actual.getExecutionTime());
		}
	}
}
