import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) {

		// Initialisierung des Sockets; bidirektionale
		// Netzwerk-Kommunikationsschnittstelle, deren Verwaltung das Betriebssystem
		// übernimmt.
		Socket s = null;
		String userName = "Client";
		if (args != null && args.length > 0) {
			userName = args[0];
		}

		// Die Klasse Scanner kann Text aus jedem Objekt lesen, das das Interface
		// Readable implementiert. Im SimpleClient wird von der Konsole, also dem
		// Standard-Eingabestrom System.in eingelesen. Die auf der Kommandozeile
		// eingegebenen und mit <Return> abgeschlossenen Texte werden so eingelesen und
		// an den SimpleServer gesendet.
		Scanner sc = null;
		String message = null;
		try {
			sc = new Scanner(System.in);
			while (!"stop".equals(message)) {

				// Einrichtung der Socketverbindung über Port 8442 zu localhost bzw.
				// rivera.wi.hs-wismar.de
				// Hochschulkennung st191442 => Port: 8442
				s = new Socket("localhost", 8442); // Für die lokale Ausführung "localhost"
				// anstelle von "rivera.wi.hs-wismar.de" verwenden
				//s = new Socket("rivera.wi.hs-wismar.de", 8442);

				// BufferedReader: Filter zur Pufferung von Eingaben, kann verwendet werden, um
				// die Performance beim Lesen von externen Dateien zu erhöhen. Da nicht jedes
				// Byte einzeln gelesen wird, verringert sich die Anzahl der Zugriffe auf den
				// externen Datenträger, und die Lesegeschwindigkeit erhöht sich.
				// s.getInputStream(): liefert InputStream, aus dem gelesen werden kann
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

				// PrintStream wird zum Schreiben von Strings verwendet
				// s.getOutputStream(): liefert OutputStream, in den geschrieben werden kann
				PrintStream out = new PrintStream(s.getOutputStream());
				// out.println(): Schreibt den gewünschten String und fügt automatisch einen
				// Zeilenumbruch an

				System.out.print(userName + ": ");

				// Einlesen der Eingabe in den String "message"
				message = sc.nextLine();

				// Schreiben von "message" in den Ausgabestream
				out.println(message);

				// out.flush(): Leert den Ausgabestream und erzwingt das Ausschreiben aller
				// gepufferten Ausgabebytes
				out.flush();

				// in.readLine(): liest eine komplette Textzeile als String
				String t = in.readLine();
				System.out.println("Server: " + t);
			}

			// Probleme bei den Input-Output-Vorgängen oder ein falscher Host-Name können
			// Exceptions werfen, die im try-catch-Block abgefangen werden
		} catch (

		IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) {
				try {
					s.close();
					// Schließen des Scanners
					sc.close();
				} catch (IOException e) {
					System.out.println("Socket nicht zu schliessen...");
					System.out.println(e.getMessage());
				}
			}
		}
	}
}