package test.java.com.nwchecker.server.service;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
		Long sample1 = taskPassService.getTaskPassEntryCount(1, null);
		assertEquals(6L, sample1.longValue());
		Long sample2 = taskPassService.getTaskPassEntryCount(2, null);
		assertEquals(2L, sample2.longValue());
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetSuccessfulTaskPassSampleSize() {
		Long sample1 = taskPassService.getSuccessfulTaskPassEntryCount(1);
		assertEquals(2L, sample1.longValue());
		Long sample2 = taskPassService.getSuccessfulTaskPassEntryCount(2);
		assertEquals(2L, sample2.longValue());
	}

	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetPagedTaskPassesForTask() {
		List<TaskPass> taskPasses = taskPassService.getPagedTaskPassesForTask(
				2, 0, 5, null, null, null);
		assertEquals(2, taskPasses.size());
		List<TaskPass> taskPasses2 = taskPassService.getPagedTaskPassesForTask(
				1, 0, 5, null, null, null);
		assertEquals(5, taskPasses2.size());
	}

	@Test
	@Ignore
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetPagedTaskPassesForTaskSorted() {
		List<TaskPass> taskPasses2 = taskPassService.getPagedTaskPassesForTask(
				1, 0, 6, null, null,null);
		Collections.sort(taskPasses2, new Comparator<TaskPass>() {
			@Override
			public int compare(TaskPass current, TaskPass other) {
				return current.getExecutionTime() > other.getExecutionTime() ? 1
						: current.getExecutionTime() == other
								.getExecutionTime() ? 0 : -1;
			}
		});
		List<TaskPass> taskPassesSorted = taskPassService
				.getPagedTaskPassesForTask(1, 0, 6, "executionTime", "asc", null);
		Iterator<TaskPass> controlled = taskPasses2.iterator();
		Iterator<TaskPass> testing = taskPassesSorted.iterator();
		while (controlled.hasNext() && testing.hasNext()) {
			TaskPass expected = controlled.next();
			TaskPass actual = testing.next();
			assertEquals((long) expected.getExecutionTime(),
					(long) actual.getExecutionTime());
		}
	}
}
