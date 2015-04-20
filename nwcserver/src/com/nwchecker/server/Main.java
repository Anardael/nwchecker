package com.nwchecker.server;


import com.nwchecker.server.dao.LanguageDAO;
import com.nwchecker.server.dao.LanguageDAOImpl;
import com.nwchecker.server.dao.RuleDAO;
import com.nwchecker.server.dao.RuleDAOImpl;
import com.nwchecker.server.model.Language;
import com.nwchecker.server.model.Rule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/spring/application-context.xml");

        RuleDAO ruleDAO = context.getBean("RuleDAO", RuleDAO.class);
        LanguageDAO languageDAO = context.getBean("LanguageDAO", LanguageDAO.class);

        //ruleDAO.deleteRuleById(2);

        List<Rule> rules = new ArrayList<Rule>();
        List<Language> languages = new ArrayList<Language>();
        for(int i = 1; i < 3; i++){
            Rule rule = new Rule();
            rule.setContent(i + "st content");
            rule.setName(i + "st name");
            rules.add(rule);

            Language language = new Language();
            language.setTag(i + "st tag");
            languages.add(language);
        }

        for(Rule r : rules){
            r.setLanguages(languages);
        }
        /*for(Language l : languages){
            l.setRules(rules);
        }*/

        /*for(Rule r : rules){
            System.out.println(r);;
        }

        for(Language l : languages){
            System.out.println(l);
        }*/

        for(Rule r : rules){
            ruleDAO.createRule(r);
        }

        /*for(Language l : languages){
            languageDAO.createLanguage(l);
        }*/
    }
}
