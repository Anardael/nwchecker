package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.service.CheckerService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Map;
import static org.junit.Assert.assertTrue;

/**
 * <h1>CheckerServiceImpl Test</h1>
 * Test for CheckerServiceImpl method.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class CheckerServiceImplTest {

    @Autowired
    private CheckerService checkerService;

    @Ignore
    @Test
    public void testCheckTask() throws Exception {
        Map<String, Object> result = checkerService.checkTask(new Task(), new byte[8], 0);

        assertTrue(result.containsKey("passed"));
        assertTrue(result.containsKey("time"));
        assertTrue(result.containsKey("memory"));

        if (!(boolean) result.get("passed")) {
            assertTrue(result.containsKey("message"));
        }
    }
}