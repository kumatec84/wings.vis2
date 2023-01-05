import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javax.naming.*;
import java.util.Hashtable;

class HelloServer {
	public static void main(String[] argv) {
		try {    
			Naming.rebind("rmi://rivera.wi.hs-wismar.de:8442/HelloServer", new Hello("Hello, RMI world!"));

			System.out.println("Hello Server is ready.");		
		} catch(Exception e) {
			System.out.println("Hello Server failed: " + e);
		}
	}
}