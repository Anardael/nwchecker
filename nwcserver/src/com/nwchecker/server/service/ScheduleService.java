package com.nwchecker.server.service;

import com.nwchecker.server.model.Contest;

/**
 * <h1>Schedule Service</h1> Service for working with server Schedule
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-11
 */
public interface ScheduleService {

	/**
	 * Refresh schedule.
	 * <p>
	 */
	void refresh();

	/**
	 * Finish a contest. If contest is static, the rating is also calculated.
	 * Status of the contest will be set to ARCHIVE.
	 * 
	 * @param contest Some Contest to be finished.
	 */

	public void finishContest(Contest contest);

	void startContest(Contest contest);

}
