package com.nwchecker.server.dao;

import com.nwchecker.server.model.Language;

import java.util.List;

public interface LanguageDAO {
	/**
	 * Add new Language to the database.
	 * 
	 * @param language
	 *            Language to be inserted.
	 */
	void createLanguage(Language language);

	/**
	 * Returns Language from database based on it's ID.
	 * 
	 * @param id
	 *            ID of the language.
	 * @return Language object from database.
	 */
	Language readLanguageById(int id);

	/**
	 * Update Language entry in the database.
	 * 
	 * @param language
	 *            Language that will be updated in the database.
	 */

	void updateLanguage(Language language);

	/**
	 * Removes Language entry from the database.
	 * 
	 * @param id
	 *            ID of Language to be removed.
	 */
	void deleteLanguageById(int id);

	/**
	 * Returns all Languages from the database.
	 * 
	 * @return List of Languages from database.
	 */

	List<Language> getAllLanguages();
}
