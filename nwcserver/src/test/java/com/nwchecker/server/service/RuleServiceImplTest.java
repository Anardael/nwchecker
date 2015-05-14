package test.java.com.nwchecker.server.service;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.dao.RuleDAO;
import com.nwchecker.server.model.Rule;
import com.nwchecker.server.service.RuleService;
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
public class RuleServiceImplTest {

    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleDAO ruleDAO;

    private List<Rule> ruleList = new LinkedList<Rule>();

    @Before
    public void init() {
        for (int i = 1; i < 5; i++) {
            Rule rule = new Rule();
            rule.setId(i);
            rule.setContent("Rule content number " + i);
            rule.setTypeId(1);
            rule.setLanguageId(1);
            ruleList.add(rule);
        }
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void getRulesByLanguageTagTest() {
        List<Rule> rules = ruleService.getRulesByLanguageTag("en");
        assertEquals(rules.size(), 2);
        for (Rule rule : rules) {
            assertEquals(rule.getLanguage().getId(), 1);
        }
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void updateRulesTest() {
        ruleService.updateRules(ruleList);
        List<Rule> rules = ruleDAO.getAllRules();
        for (Rule rule : rules) {
            assertEquals(rule.getLanguage().getId(), 1);
            assertEquals(rule.getTypeContest().getId(), 1);
        }
    }
}
