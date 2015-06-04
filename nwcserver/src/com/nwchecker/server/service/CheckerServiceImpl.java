package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.TaskTestResult;
import com.nwchecker.server.utils.CheckerMessage;
import com.nwchecker.server.utils.CheckerResponse;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service(value = "CheckerService")
public class CheckerServiceImpl implements CheckerService {
	static final int PORT = 9999;

	@Override
	public List<CheckerResponse> sendSolutionToChecker(CheckerMessage message) {
		List<CheckerResponse> checkerResults = new LinkedList<CheckerResponse>();
		Iterator<TaskData> it = message.getInOutDataPairs().iterator();
		while (it.hasNext()) {
			CheckerResponse mockResult = new CheckerResponse();
			Random rd = new Random();
			boolean passed = rd.nextBoolean();
			if (passed) {
				mockResult.setSuccess(1);
				mockResult.setExecutionTime(rd.nextInt(5000));
				mockResult.setMemoryUsed(rd.nextInt(5000));
			}
			//failed
			else{
				mockResult.setSuccess(0);
				mockResult.setExecutionTime(rd.nextInt(20000));
				mockResult.setMemoryUsed(rd.nextInt(20000));
			}
			checkerResults.add(mockResult);
			it.next();
		}
		return checkerResults;
	}

	@Override
	public Map<String, Object> checkTask(Task task, int compilerId,
			byte[] userSolution, TaskPass taskPass) {
		//Create message to be sent to checker
		CheckerMessage message = new CheckerMessage();
		message.setCompilerId(compilerId);
		message.setInOutDataPairs(task.getInOutData());
		message.setUserSolution(userSolution);
		
		//Send data to checker
		List<CheckerResponse> checkerResults = sendSolutionToChecker(message);
		
		//Process data from checker
		Integer successful = 0;
		List<TaskTestResult> testResults = new LinkedList<TaskTestResult>();
		
		for (CheckerResponse atomicResponse : checkerResults) {
			TaskTestResult testResult = new TaskTestResult();
			if (atomicResponse.getSuccess() > 0) {
				successful++;
				testResult.setExecutionTime(atomicResponse.getExecutionTime());
				testResult.setMemoryUsed(atomicResponse.getMemoryUsed());
				testResult.setRate(atomicResponse.getSuccess());
			}
			testResult.setTaskPass(taskPass);
			testResults.add(testResult);
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("results", testResults);
		response.put("successful", successful);
		response.put("passed", successful.equals(task.getInOutData().size()));

		return response;
	}
}
/*
 * Message to checker: 1. Compiler(ID?) 2. User's solution file.(byte array) 3.
 * Verification script (String) 4. List of input&output data: 4.1. Input
 * data(byte array) 4.2. Output data(byte array)
 */

/*
 * Message from checker: Results of user's program: list of data: 1. Data that
 * indicates user's success in passing the task(boolean, integer, double?) 2.
 * Execution time(int(long?), milliseconds) 3. Memory Used (int(long?), kb) 4.
 */

/*
 * try { Socket connectionSocket = new Socket("127.0.0.1", PORT);
 * ObjectOutputStream dataOutput = new ObjectOutputStream(
 * connectionSocket.getOutputStream()); CheckerMessage message = new
 * CheckerMessage(); message.setCompilerId(1); dataOutput.writeObject(message);
 * ObjectInputStream dataInput = new ObjectInputStream(
 * connectionSocket.getInputStream()); CheckerResponse response =
 * (CheckerResponse) dataInput.readObject(); connectionSocket.close(); return
 * response.getResponse(); } catch (IOException e) { e.printStackTrace(); }
 * catch (ClassNotFoundException e) { e.printStackTrace(); }
 */
