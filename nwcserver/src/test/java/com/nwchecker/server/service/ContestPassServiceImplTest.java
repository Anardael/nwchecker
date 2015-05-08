package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * <h1>ContestPassServiceImpl Test</h1>
 * Test for ContestPassServiceImpl methods.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-10
 */
//TODO:CHANGE BEFORE DEPLOY!
//TODO:CHANGE BEFORE DEPLOY!
//TODO:CHANGE BEFORE DEPLOY!
//TODO:CHANGE BEFORE DEPLOY!

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class ContestPassServiceImplTest {

    @Autowired
    private ContestPassService contestPassService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private UserService userService;

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testSaveContestPass() throws Exception {
        int contestsBefore = contestPassService.getContestPasses(2).size();

        ContestPass contestPass = new ContestPass();
        contestPass.setContest(contestService.getContestByID(2));
        contestPass.setUser(userService.getUserById(4));
        contestPassService.saveContestPass(contestPass);

        assertEquals(contestPassService.getContestPasses(2).size(), contestsBefore + 1);
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testUpdateContestPass() throws Exception {
        ContestPass contestPass = contestPassService.getContestPasses(1).get(0);
        contestPass.setRank(9001);

        contestPassService.updateContestPass(contestPass);
        assertEquals(contestPass.getRank(), contestPassService.getContestPasses(1).get(0).getRank());
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testCheckTask() throws Exception {
        ContestPass contestPass = contestPassService.getContestPasses(1).get(0);
        Task task = contestPass.getContest().getTasks().get(0);
//TODO:CHANGE BEFORE DEPLOY!
      //TODO:CHANGE BEFORE DEPLOY!
      //TODO:CHANGE BEFORE DEPLOY!
      //TODO:CHANGE BEFORE DEPLOY!
      //TODO:CHANGE BEFORE DEPLOY!
        assertNotNull(contestPassService.checkTask(true, contestPass, task, 1, new byte[8], new User()));
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetContestPasses() throws Exception {
        assertTrue(!contestPassService.getContestPasses(1).isEmpty());
    }
}