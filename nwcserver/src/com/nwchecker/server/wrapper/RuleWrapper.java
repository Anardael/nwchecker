package com.nwchecker.server.wrapper;


import com.nwchecker.server.model.Rule;

import java.util.ArrayList;
import java.util.List;


public class RuleWrapper {
    private List<Rule> ruleList = null;

    public RuleWrapper() {
        this.ruleList = new ArrayList<Rule>();
    }

    public RuleWrapper(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }
}
