package com.nwchecker.server;


import com.nwchecker.server.dao.LanguageDAO;
import com.nwchecker.server.dao.LanguageDAOImpl;
import com.nwchecker.server.dao.RuleDAO;
import com.nwchecker.server.dao.RuleDAOImpl;
import com.nwchecker.server.model.Language;
import com.nwchecker.server.model.Rule;
import com.nwchecker.server.service.RuleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void createData(RuleDAO ruleDAO, LanguageDAO languageDAO){
        List<Language> languages = new ArrayList<Language>();
        Language language1 = new Language();
        language1.setTag("ENG");
        languages.add(language1);
        Language language2 = new Language();
        language2.setTag("UA");
        languages.add(language2);
        Language language3 = new Language();
        language3.setTag("RUS");
        languages.add(language3);


        List<Rule> rules = new ArrayList<Rule>();
        for(Language language : languages){
            Rule rule1 = new Rule();
            rule1.setContent(language.getTag() + " language content");
            rule1.setType(Rule.Type.ICM);
            rule1.setLanguage(language);
            Rule rule2 = new Rule();
            rule2.setContent(language.getTag() + " language content");
            rule2.setType(Rule.Type.ICM);
            rule2.setLanguage(language);

            language.addRule(rule1);
            language.addRule(rule2);
        }
        /*for(Language l : languages){
            System.out.println("- " + l);
            for(Rule r : l.getRules()){
                System.out.println("     - " + r);
            }
        }*/
        for(Language l : languages){
            languageDAO.createLanguage(l);
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/spring/application-context.xml");

        RuleDAO ruleDAO = context.getBean("RuleDAO", RuleDAO.class);
        LanguageDAO languageDAO = context.getBean("LanguageDAO", LanguageDAO.class);

        /*RuleService ruleService = context.getBean("RuleService", RuleService.class);

        List<Rule> rules = ruleService.getRulesByLanguageTag("ENG");
        for(Rule rule : rules){
            System.out.println(rule);
        }*/


        //createData(ruleDAO, languageDAO);

        //ruleDAO.deleteRuleById(2);
        //languageDAO.deleteLanguageById(2);
    }
}
