import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerSecure {
	public static void main(String[] args) {
		try {			
			// Es wird die eigene Implementiert des SecurityManagers "PortSecurityManager" gesetzt. 
			
			// create a security manager
			SecurityManager sm = new PortSecurityManager();
			
			// set the system security manager
			System.setSecurityManager(sm);

			// Das try-with-resources Statement ermoeglicht die Deklaration von Ressourcen,
			// die am Ende des try Blocks automatisch geschlossen werden.

			// ServerSocket repraesentiert einen Server, dessen Konstruktor die Nummer des
			// Ports uebergeben bekommt, an dem der Server horchen soll.
			try (ServerSocket ss = new ServerSocket(8442)) { // Hochschulkennung st191442 => Port: 8442
				System.out.println("START SimpleServer!");

				String t = null;

				// Die Variable stop dient als Bedindung, bei welcher der Server gestoppt bzw.
				// die while-Schleife nicht fortgesetzt wird. Sie wird innerhalb des Servers auf
				// true gesetzt, wenn dieser die Nachricht "stop" vom Client empfaengt.
				boolean stop = false;
				
				while (!stop) {
					
					// Nach Einrichten des Sockets wird durch die Methode accept() eine Verbindung
					// innerhalb einer Endlosschleife hergestellt.
					Socket s = ss.accept();
					
					// Innerhalb der while-Schleife werden auf gleiche Weise, wie in
					// SimpleClient.java beschrieben, Input- und OutputStreams gelesen und
					// geschrieben. Diese werden wieder vom Socket geliefert.
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}