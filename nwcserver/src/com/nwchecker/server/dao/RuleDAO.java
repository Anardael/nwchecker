package com.nwchecker.server.dao;

import com.nwchecker.server.model.Rule;
import java.util.List;

public interface RuleDAO {
    void createRule(Rule rule);
    Rule readRuleById(int id);
    void updateRule(Rule rule);
    void deleteRuleById(int id);
    List<Rule> getAllRules();
}
