package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.model.TypeContest;
import com.nwchecker.server.service.TypeContestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class TypeContestServiceImplTest {
    @Autowired
    TypeContestService typeContestService;

    private List<TypeContest> typeContestList = new LinkedList<TypeContest>();

    @Before
    public void init() {
        for (int i = 1; i < 3; i++) {
            TypeContest typeContest = new TypeContest();
            typeContest.setId(i);
            typeContest.setName("Test type");
            typeContest.setDynamic(true);
            typeContestList.add(typeContest);
        }
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void getAllTypeContestTest() {
        List<TypeContest> list = typeContestService.getAllTypeContest();
        assertEquals(list.size(), 2);
    }
}
