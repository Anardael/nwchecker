package com.nwchecker.server.dao;

import com.nwchecker.server.model.Rule;
import java.util.List;

public interface RuleDAO {
    public void createRule(Rule rule);
    public Rule readRuleById(int id);
    public void updateRule(Rule rule);
    public void deleteRuleById(int id);
    public List<Rule> getRulesByLanguageId(int languageId);
    public List<Rule> getAllRules();
}
