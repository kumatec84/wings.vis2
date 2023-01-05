import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(8442)) { // Hochschulkennung st191442 => Port: 8442
			System.out.println("START SimpleServer!");
			String t = null;
			boolean stop = false;
			while (!stop) {
				Socket s = ss.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintStream out = new PrintStream(s.getOutputStream());
				t = in.readLine();
				if (t != null) {
					System.out.println("Server receives: " + t);
					if (!"stop".equals(t)) {
						out.println("Hi client!");
					} else {
						stop = true;
						out.println("The server will be stopped!");
					}
					out.flush();
				}
			}
			System.out.println("STOP SimpleServer!");
		}
	}
}