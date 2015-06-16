package com.nwchecker.server.dao;

import com.nwchecker.server.model.Rule;

import java.util.List;

public interface RuleDAO {
	/**
	 * Add Rule to the database.
	 * 
	 * @param rule
	 *            Rule to be added to the database.
	 */
	void createRule(Rule rule);

	/**
	 * Returns Rule form the database based on it's ID.
	 * 
	 * @param id
	 *            ID of the rule
	 * @return Rule object from database.
	 */
	Rule readRuleById(int id);

	/**
	 * Update Rule object in database.
	 * 
	 * @param rule
	 *            Rule to be updated.
	 */
	void updateRule(Rule rule);

	/**
	 * Remove Rule entry in the database.
	 * 
	 * @param id
	 *            ID of the Rule.
	 */
	void deleteRuleById(int id);

	/**
	 * Returns all rules from database based on their language tag.
	 * 
	 * @param tag
	 *            Language tag.
	 * @return List of rules that have certain language.
	 */
	List<Rule> getRulesByLanguageTag(String tag);

	/**
	 * Updates Rule's content in the database based on it's ID.
	 * 
	 * @param id
	 *            ID of the rule.
	 * @param content
	 *            Content to be updated.
	 */
	void updateRuleContentById(int id, String content);

	/**
	 * Return all Rule objects from database.
	 * 
	 * @return List of Rule objects from database.
	 */
	List<Rule> getAllRules();
}
