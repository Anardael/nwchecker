package nwcserver.nwc_dummy_checker;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(99);

			Socket clientSocket = serverSocket.accept();
			try {

				ObjectInputStream dataInput = new ObjectInputStream(
						clientSocket.getInputStream());
				CheckerMessage message = (CheckerMessage) dataInput
						.readObject();
				dataInput.close();

				HashMap<String, Object> checkerResult = TaskChecker
						.checkTask(message);
				ObjectOutputStream dataOutput = new ObjectOutputStream(
						clientSocket.getOutputStream());
				CheckerResponse checkerResponse = new CheckerResponse();
				checkerResponse.setResponse(checkerResult);
				dataOutput.writeObject(checkerResponse);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			clientSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}