package com.nwchecker.server.dao;

import com.nwchecker.server.model.TypeContest;

import java.util.List;

public interface TypeContestDAO {
	/**
	 * Add TypeContest to the database.
	 * 
	 * @param typeContest
	 */
	void createType(TypeContest typeContest);

	/**
	 * Returns TypeContest from database based on it's ID.
	 * 
	 * @param id
	 *            ID of TypeContest.
	 * @return TypeContest from database.
	 */
	TypeContest readTypeById(int id);

	/**
	 * Update TypeContest object in the database.
	 * 
	 * @param typeContest
	 *            TypeContest to be updated.
	 */
	void updateType(TypeContest typeContest);

	/**
	 * Remove TypeContest from databse.
	 * 
	 * @param id
	 *            ID of TypeContest to be deleted.
	 */
	void deleteTypeById(int id);

	/**
	 * Returns all TypeContests from database.
	 * 
	 * @return List of TypeContests.
	 */
	List<TypeContest> getAllTypes();
}
