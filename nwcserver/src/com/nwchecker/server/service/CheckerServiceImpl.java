package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskPass;
import com.nwchecker.server.model.TaskTestResult;
import com.nwchecker.server.utils.messages.CheckerMessage;
import com.nwchecker.server.utils.messages.CheckerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

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
	public Map<String, Object> checkTask(Task task, String compilerExtension,
			byte[] userSolution, TaskPass taskPass) throws ClassNotFoundException, IOException {
		// Create message to be sent to checker
		CheckerMessage checkerMessage = new CheckerMessage();
		checkerMessage.setInputData(new ArrayList<byte[]>());
		checkerMessage.setOutputData(new ArrayList<byte[]>());
		checkerMessage.setScore(new ArrayList<Integer>());
		checkerMessage.setTime(new ArrayList<Integer>());
		checkerMessage.setMemory(new ArrayList<Integer>());
		checkerMessage.setUserSolution(userSolution);
		checkerMessage.setCheckingScript(task.getScriptForVerification());
		checkerMessage.setInputFileName(task.getInputFileName());
		checkerMessage.setOutputFileName(task.getOutputFileName());

		for (TaskData taskData : task.getInOutData()) {
			// add in/out data to message
		    checkerMessage.getInputData().add(taskData.getInputData());
		    checkerMessage.getOutputData().add(taskData.getOutputData());
		    checkerMessage.getMemory().add(task.getMemoryLimit());
		    checkerMessage.getTime().add(task.getTimeLimit());
		    checkerMessage.getScore().add(1);
		}

		// send data to checker
		CheckerResponse checkerResponse = sendSolutionToChecker(checkerMessage);

		// Process data from checker
		Integer successful = 0;
		List<TaskTestResult> testResults = new LinkedList<TaskTestResult>();
		for (int i=0;i<checkerResponse.getResponse().length();i++) {
			TaskTestResult testResult = new TaskTestResult();
			int rate = 0;
			if (checkerResponse.getResponse().charAt(i) =='+'||checkerResponse.getResponse().charAt(i) =='P') {
				successful++;
				rate=1;
			}
			testResult.setRate(rate);
			testResult.setTaskPass(taskPass);
			testResults.add(testResult);
		}
		// Process data from checker

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("results", testResults);
		response.put("score", checkerResponse.getScore());
		response.put("log", checkerResponse.getLog());
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
	public CheckerResponse sendSolutionToChecker(CheckerMessage message) throws ClassNotFoundException, IOException {
		CheckerResponse checkerResponse=null;
		try {
			LOG.info("Attempting to send data to checker");
			Socket socket = new Socket();
			ObjectOutputStream dataOutput = new ObjectOutputStream(socket.getOutputStream());
			dataOutput.writeObject(message);
			dataOutput.flush();
			ObjectInputStream dataInput = new ObjectInputStream(socket.getInputStream());
			CheckerResponse response = (CheckerResponse) dataInput.readObject();
			socket.close();
			LOG.info("Data recieved successfully.");
		} catch (IOException e) {
			LOG.warn("Failed to connect to checker");
			throw e;
		}
		return checkerResponse;
	}

}