package test.java.com.nwchecker.server.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.service.RatingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/forTests/context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class RatingServiceImplTest {
	@Autowired
	RatingService ratingService;
	@Test
	@DatabaseSetup("classpath:/forTests/dataset.xml")
	public void testGetJsonListForContestPassByContestId(){
		assertEquals(0,ratingService.getJsonListForContestPassByContestId(1).size());
	}
}
