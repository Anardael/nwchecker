package nwcserver.nwc_dummy_checker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nwchecker.server.utils.CheckerMessage;
import com.nwchecker.server.utils.CheckerResponse;

public class Connect implements Runnable {
	Socket socket;

	public Connect(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			ObjectInputStream dataInput = new ObjectInputStream(
					socket.getInputStream());
			CheckerMessage message = (CheckerMessage) dataInput.readObject();
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LinkedHashMap<String, Object> checkerResult = TaskChecker
					.checkTask(message);
			Iterator<Object> iterator = checkerResult.values().iterator();
			
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}
			
			ObjectOutputStream dataOutput = new ObjectOutputStream(
					socket.getOutputStream());
			CheckerResponse checkerResponse = new CheckerResponse();
			checkerResponse.setResponse(checkerResult);
			dataOutput.writeObject(checkerResponse);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
