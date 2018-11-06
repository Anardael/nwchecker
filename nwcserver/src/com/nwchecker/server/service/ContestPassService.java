package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <h1>Contest Pass Service</h1> Service that used in Contest passing.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-21
 */
public interface ContestPassService {

	/**
	 * Add ContestPass in database.
	 * <p>
	 *
	 * @param contestPass
	 *            ContestPass that will be inserted in DB
	 */
	void saveContestPass(ContestPass contestPass);

	/**
	 * Update existing ContestPass in database.
	 *
	 * @param contestPass
	 *            ContestPass that will be updated in DB
	 */
	void updateContestPass(ContestPass contestPass);

	/**
	 * Check some Task and return result.
	 * <p>
	 *
	 * @param save
	 *            this param indicates save to database record or no.
	 * @param contestPass
	 *            ContestPass that Task belongs
	 * @param task
	 *            Task Task for checking
	 * @param compilerId
	 *            Unique ID of compiler
	 * @param file
	 *            Source code files
	 * @return
	 */
	Map<String, Object> checkTask(ContestPass contestPass, Task task,
			int compilerId, byte[] file, User user)  throws ClassNotFoundException, IOException;

	/**
	 * Returns list of passed contests for specific Contest.
	 * <p>
	 *
	 * @param contestId
	 *            ID of specific Contest
	 * @return List of contestPasses for specific Contest.
	 */
	List<ContestPass> getContestPasses(int contestId);

	/**
	 * Returns list of ranked ContestPasses.
	 *
	 * @param contestId ID of specific contest
	 * @return List of contestPasses for specific Contest.
	 */
	List<ContestPass> getValidContestPasses(int contestId);

	/**
	 * Return map of user's contest results.
	 * @param userName Name of a user.
	 * @param contest Specific contest.
	 * @return Data in pairs of <taskId, result>
	 */

	Map<Integer, Boolean> getTaskResultsForContestByUserName(String userName,
			Contest contest);

	/**
	 * Check if User has contestPass for this contest.
	 *
	 * @param userName
	 *            Username of the User
	 * @param contest
	 *            Contest
	 * @return true if user has the contestPass for this contest, false
	 *         otherwise.
	 */
	boolean checkContestPassByUserName(String userName, Contest contest);
}
