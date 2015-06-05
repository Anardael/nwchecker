package com.nwchecker.server.service;

import com.google.protobuf.ByteString;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.TaskTestResult;
import com.nwchecker.server.utils.messages.CheckerMessageProto.CheckerMessage;
import com.nwchecker.server.utils.messages.CheckerMessageProto.CheckerMessage.DataPair;
import com.nwchecker.server.utils.messages.CheckerResponseProto.CheckerResponse;
import com.nwchecker.server.utils.messages.CheckerResponseProto.CheckerResponse.AtomicResponse;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service(value = "CheckerService")
public class CheckerServiceImpl implements CheckerService {
	static final int PORT = 9999;
	
	@Override
	public Map<String, Object> checkTask(Task task, int compilerId,
			byte[] userSolution, TaskPass taskPass) {
		// Create message to be sent to checker
		CheckerMessage.Builder checkerMessageBuilder = CheckerMessage
				.newBuilder().setCompilerId(compilerId)
				.setUserSolution(ByteString.copyFrom(userSolution));

		for (TaskData taskData : task.getInOutData()) {
			// add in/out data to message
			CheckerMessage.DataPair.Builder dataPairBuilder = CheckerMessage.DataPair
					.newBuilder();
			dataPairBuilder.setInputData(ByteString.copyFrom(taskData
					.getInputData()));
			dataPairBuilder.setOutputData(ByteString.copyFrom(taskData
					.getOutputData()));
			CheckerMessage.DataPair dataPair = dataPairBuilder.build();
			checkerMessageBuilder.addInOutData(dataPair);
		}
		CheckerMessage builtMessage = checkerMessageBuilder.build();
		// send data to checker
		CheckerResponse checkerResponse = sendSolutionToChecker(builtMessage);
		
		// Process data from checker
		Integer successful = 0;
		List<TaskTestResult> testResults = new LinkedList<TaskTestResult>();
		for (AtomicResponse atomicResponse : checkerResponse.getTestDataList()) {
			TaskTestResult testResult = new TaskTestResult();
			if (atomicResponse.getSuccess() > 0) {
				successful++;
			}
			testResult.setExecutionTime(atomicResponse.getExecutionTime());
			testResult.setMemoryUsed(atomicResponse.getMemoryUsed());
			testResult.setRate(atomicResponse.getSuccess());
			testResult.setTaskPass(taskPass);
			testResults.add(testResult);
		}
		// Process data from checker

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("results", testResults);
		response.put("successful", successful);
		response.put("passed", successful.equals(task.getInOutData().size()));

		return response;
	}

	@Override
	public CheckerResponse sendSolutionToChecker(CheckerMessage message) {
		/*
		 * int compilerId = message.getCompilerId(); byte[] userSolution =
		 * message.getUserSolution().toByteArray();
		 */// TODO: parse this as XML
		CheckerResponse.Builder responseBuilder = CheckerResponse.newBuilder();
		Random rd = new Random();
		for (@SuppressWarnings("unused")
		DataPair dataPair : message.getInOutDataList()) {
			CheckerResponse.AtomicResponse.Builder atomicResponseBuilder = CheckerResponse.AtomicResponse
					.newBuilder();
			boolean passed = rd.nextBoolean();
			if (passed) {
				atomicResponseBuilder.setSuccess(1);
				atomicResponseBuilder.setExecutionTime(rd.nextInt(5000));
				atomicResponseBuilder.setMemoryUsed(rd.nextInt(5000));
			}
			// failed
			else {
				atomicResponseBuilder.setSuccess(0);
				atomicResponseBuilder.setExecutionTime(rd.nextInt(20000));
				atomicResponseBuilder.setMemoryUsed(rd.nextInt(20000));
			}
			responseBuilder.addTestData(atomicResponseBuilder.build());
		}
		return responseBuilder.build();
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
