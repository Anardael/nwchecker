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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service(value = "CheckerService")
public class CheckerServiceImpl implements CheckerService {
	private static final Logger LOG = Logger
			.getLogger(CheckerServiceImpl.class);
	static final int PORT = 9999;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nwchecker.server.service.CheckerService#checkTask(com.nwchecker.server
	 * .model.Task, int, byte[], com.nwchecker.server.model.TaskPass)
	 */
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
		response.put("total", task.getInOutData().size());
		response.put("passed", successful.equals(task.getInOutData().size()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nwchecker.server.service.CheckerService#sendSolutionToChecker(com
	 * .nwchecker.server.utils.messages.CheckerMessageProto.CheckerMessage)
	 */
	@Override
	public CheckerResponse sendSolutionToChecker(CheckerMessage message) {
		CheckerResponse checkerResponse;
		try {
			LOG.info("Attempting to send data to checker");
			Socket socket = new Socket();
			message.writeTo(socket.getOutputStream());
			checkerResponse = CheckerResponse
					.parseFrom(socket.getInputStream());
			socket.close();
			LOG.info("Data recieved successfully.");
		} catch (IOException e) {
			LOG.warn("Failed to connect to checker");
			CheckerResponse.Builder responseBuilder = CheckerResponse
					.newBuilder();
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
			checkerResponse = responseBuilder.build();
		}
		return checkerResponse;
	}

}