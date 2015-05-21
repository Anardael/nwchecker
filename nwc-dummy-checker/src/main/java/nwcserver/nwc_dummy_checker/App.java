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
			ServerSocket socketListener = new ServerSocket(99);
			while (true) {
				Socket clientSocket = socketListener.accept();
				new Thread(new Connect(clientSocket)).run();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}