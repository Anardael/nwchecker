package com.nwchecker.server.service;


import com.nwchecker.server.dao.LanguageDAO;
import com.nwchecker.server.dao.RuleDAO;
import com.nwchecker.server.model.Rule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RuleService")
public class RuleServiceImpl implements RuleService {
    private static final Logger LOG = Logger.getLogger(RuleServiceImpl.class);

    @Autowired
    private RuleDAO ruleDAO;

    @Autowired
    private LanguageDAO languageDAO;

    @Override
    public List<Rule> getRulesByLanguageTag(String tag) {
        LOG.debug("Start method getRulesByLanguageTag. Parameter: tag =  " + tag);
        int languageId = languageDAO.getLanguageIdByTag(tag);

        return ruleDAO.getRulesByLanguageId(languageId);
    }

    @Override
    public void updateRules(List<Rule> rules) {
        LOG.debug("Start method updateRules...");
        for (Rule r : rules) {
            ruleDAO.updateRule(r);
        }
    }
}
