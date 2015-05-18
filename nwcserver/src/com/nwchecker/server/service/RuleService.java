package com.nwchecker.server.service;


import com.nwchecker.server.model.Rule;

import java.util.List;

/**
 * <h1>Rule Service</h1>
 * Service that can get by language tag and update rule.
 * <p>
 *
 * @author Vitalik Kulishenko
 * @version 1.0
 */
public interface RuleService {
    /**
     * Return Rules from database by Language tag.
     * <p>
     *
     * @param tag
     * @return Rules from database
     */
    List<Rule> getRulesByLanguageTag(String tag);

    /**
     * Update rule content into database.
     * <p>
     *
     * @param id
     * @param content
     */
    void updateRuleContentById(int id, String content);
}
