package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ScoreCalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * <h1>ScoreCalculationServiceImpl Test</h1>
 * Test for ScoreCalculationServiceImpl methods.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class ScoreCalculationServiceImplTest {

    @Autowired
    private ScoreCalculationService scoreCalculationService;
    @Autowired
    private ContestPassService contestPassService;

    @Test    
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testCalculateScore() throws Exception {
        scoreCalculationService.calculateScore(1);
        List<ContestPass> results=contestPassService.getContestPasses(1);
        assertTrue(results.get(0).getUser().getUserId()==3);
        assertTrue(results.get(1).getUser().getUserId()==4);
    }
}