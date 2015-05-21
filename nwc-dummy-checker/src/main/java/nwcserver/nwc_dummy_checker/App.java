package nwcserver.nwc_dummy_checker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

import com.nwchecker.server.utils.CheckerMessage;
import com.nwchecker.server.utils.CheckerResponse;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(99);
			System.out.println("It works");

			Socket clientSocket = serverSocket.accept();
			System.out.println("accepted connection");
			try {
				ObjectInputStream dataInput = new ObjectInputStream(
						clientSocket.getInputStream());
				CheckerMessage message = (CheckerMessage) dataInput
						.readObject();
				System.out.println("read data");

				LinkedHashMap<String, Object> checkerResult = TaskChecker
						.checkTask(message);
				ObjectOutputStream dataOutput = new ObjectOutputStream(
						clientSocket.getOutputStream());
				CheckerResponse checkerResponse = new CheckerResponse();				
				checkerResponse.setResponse(checkerResult);
				dataOutput.writeObject(checkerResponse);
				System.out.println("sent data");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			clientSocket.close();
			serverSocket.close();
			} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}