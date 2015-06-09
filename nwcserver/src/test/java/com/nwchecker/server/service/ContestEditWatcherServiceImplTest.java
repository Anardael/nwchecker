package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.nwchecker.server.service.ContestEditWatcherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.context.request.async.DeferredResult;

import static org.junit.Assert.assertEquals;

/**
 * <h1>ContestEditWatcherServiceImpl Test</h1>
 * Test for ContestEditWatcherServiceImpl methods.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ContestEditWatcherServiceImplTest {

    @Autowired
    private ContestEditWatcherService contestEditWatcherService;

}