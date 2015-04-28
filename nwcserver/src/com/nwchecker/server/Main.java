package com.nwchecker.server;


import com.nwchecker.server.dao.*;
import com.nwchecker.server.model.Language;
import com.nwchecker.server.model.Rule;
import com.nwchecker.server.model.Type;
import com.nwchecker.server.service.RuleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void createData(RuleDAO ruleDAO, LanguageDAO languageDAO, TypeDAO typeDAO){
        List<Language> languages = new ArrayList<Language>();
        Language language1 = new Language();
        language1.setTag("EN");
        languages.add(language1);
        Language language2 = new Language();
        language2.setTag("UA");
        languages.add(language2);
        Language language3 = new Language();
        language3.setTag("RUS");
        languages.add(language3);

        List<Type> types = new ArrayList<Type>();
        Type type1 = new Type();
        type1.setName("ACM");
        types.add(type1);
        Type type2 = new Type();
        type2.setName("PC-UA");
        types.add(type2);

        List<Rule> rules = new ArrayList<Rule>();
        for(Language language : languages){
            Rule rule1 = new Rule();
            rule1.setContent(language.getTag() + " language content");
            rule1.setType(type1);
            rule1.setLanguage(language);
            Rule rule2 = new Rule();
            rule2.setContent(language.getTag() + " language content");
            rule2.setType(type2);
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
        for(Type t : types){
            typeDAO.createType(t);
        }

        for(Language l : languages){
            languageDAO.createLanguage(l);
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/spring/application-context.xml");

        RuleDAO ruleDAO = context.getBean("RuleDAO", RuleDAO.class);
        LanguageDAO languageDAO = context.getBean("LanguageDAO", LanguageDAO.class);
        TypeDAO typeDAO = context.getBean("TypeDAO", TypeDAO.class);

        RuleService ruleService = context.getBean("RuleService", RuleService.class);

        /*Rule rule = new Rule();
        rule.setId(1);
        rule.setType(Rule.Type.ICM);
        rule.setContent();
        rule.setLanguageId(1);
        ruleDAO.updateRule(rule);*/

        /*Rule rule = new Rule();
        rule.setId(1);
        rule.setType(Rule.Type.ICM);
        rule.setContent("ENG language content");
        rule.setLanguageId(1);
        ruleDAO.updateRule(rule);

        Rule rule2 = new Rule();
        rule2.setId(2);
        rule2.setType(Rule.Type.POP);
        rule2.setContent("ENG language content");
        rule2.setLanguageId(1);
        ruleDAO.updateRule(rule2);*/

        /*Language language = new Language();
        language.setId(1);
        language.setTag("EN");
        languageDAO.updateLanguage(language);*/
        /*RuleService ruleService = context.getBean("RuleService", RuleService.class);

        List<Rule> rules = ruleService.getRulesByLanguageTag("ENG");
        for(Rule rule : rules){
            System.out.println(rule);
        }*/


        //createData(ruleDAO, languageDAO, typeDAO);

        //typeDAO.deleteTypeById(2);
        //ruleDAO.deleteRuleById(2);
        //languageDAO.deleteLanguageById(2);

        //ruleService.getRulesByLanguageTag("UA");
    }
}
