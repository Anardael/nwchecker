package com.nwchecker.server.service;


import com.nwchecker.server.model.Rule;

import java.util.List;

public interface RuleService {
    public List<Rule> getRulesByLanguageTag(String tag);
}
