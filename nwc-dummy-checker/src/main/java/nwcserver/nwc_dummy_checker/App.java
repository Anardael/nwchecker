package nwcserver.nwc_dummy_checker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			ServerSocket socketListener = new ServerSocket(9999);
			while (true) {
				Socket clientSocket = socketListener.accept();
				System.out.println("1");
				new Thread(new Connect(clientSocket)).run();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}