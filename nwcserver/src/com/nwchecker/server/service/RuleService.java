package com.nwchecker.server.service;


import com.nwchecker.server.model.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> getRulesByLanguageTag(String tag);
    void updateRules(List<Rule> rules);
    void updateRulesByLanguageTag(List<Rule> rules, String tag);
}
