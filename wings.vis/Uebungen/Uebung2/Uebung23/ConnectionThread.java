import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ConnectionThread extends Thread {
	private Socket socket;
	private String userName;

	public ConnectionThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		System.out.println("START ConnectionThread!");

		String message = null;
		boolean stop = false;
		try {
			while (!stop) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream out = new PrintStream(socket.getOutputStream());
				message = in.readLine();
				if (message != null) {
					System.out.println("[" + this.getName() + "] receives: " + message);

					if (!"stop".equals(message)) {
						out.println("Hi client!");
					} else {
						stop = true;
						out.println("The ConnectionThread will be stopped!");
					}
					out.flush();
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		System.out.println("STOP ConnectionThread!");
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}