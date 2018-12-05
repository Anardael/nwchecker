package com.nwchecker.server.service;


import com.nwchecker.server.dao.RuleDAO;
import com.nwchecker.server.model.Rule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("RuleService")
@Transactional
public class RuleServiceImpl implements RuleService {
    private static final Logger LOG = Logger.getLogger(RuleServiceImpl.class);

    @Autowired
    private RuleDAO ruleDAO;

    @Override
    public List<Rule> getRulesByLanguageTag(String tag) {
        LOG.debug("Start method getRulesByLanguageTag. Param = {tag: " + tag + "}");

        return ruleDAO.getRulesByLanguageTag(tag);
    }

    @Override
    public void updateRuleContentById(int id, String content) {
        LOG.debug("Start method updateRuleContent. Param = {id: " + id + ", content: " + content + "}");
        ruleDAO.updateRuleContentById(id, content);
    }
}
