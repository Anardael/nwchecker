package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.utils.messages.CheckerMessage;
import com.nwchecker.server.utils.messages.CheckerResponse;

import java.io.IOException;
import java.util.Map;

/**
 * <h1>Checker Service</h1> Service that checks Tasks.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-11
 */
public interface CheckerService {
	/**
	 * Send the solution to the checker node.
	 *
	 * @param message Protobuf message that encapsulates User's solution to be sent to checker.
	 * @return Protobuf message that encapsulates Checker's response.
	 */

	public CheckerResponse sendSolutionToChecker(CheckerMessage message) throws ClassNotFoundException, IOException;

	/**
	 * Check some Task and return result.
	 * <p>
	 *
	 * @param task
	 *            Task for checking
	 * @param compilerExtension
	 *            Extension for the compiler
	 * @param file
	 *            Source code files
	 * @param taskPass
	 *            Entity that represents User's Task submit
	 * @return Result of Task checking
	 */

	Map<String, Object> checkTask(Task task, String compilerExtension,
			byte[] userSolution, TaskPass taskPass) throws ClassNotFoundException, IOException;
}
