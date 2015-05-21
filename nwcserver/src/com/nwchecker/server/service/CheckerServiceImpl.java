package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.utils.CheckerMessage;
import com.nwchecker.server.utils.CheckerResponse;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

@Service(value = "CheckerService")
public class CheckerServiceImpl implements CheckerService {
	static final int PORT = 99;

	@Override
	public Map<String, Object> checkTask(Task task, byte[] file, int compilerId) {
		try {
			Socket connectionSocket = new Socket("127.0.0.1", PORT);
			ObjectOutputStream dataOutput = new ObjectOutputStream(
					connectionSocket.getOutputStream());
			CheckerMessage message = new CheckerMessage();
			message.setCompilerId(1);
			dataOutput.writeObject(message);
			ObjectInputStream dataInput = new ObjectInputStream(
					connectionSocket.getInputStream());
			CheckerResponse response = (CheckerResponse) dataInput.readObject();
			connectionSocket.close();
			Iterator<Object> iterator = response.getResponse().values()
					.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			return response.getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
