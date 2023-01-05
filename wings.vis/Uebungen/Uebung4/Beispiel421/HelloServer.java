import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javax.naming.*;
import java.util.Hashtable;

class HelloServer {
	public static void main(String[] argv) {
		try {
//			Option 1
		    LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:8442/HelloServer", new Hello("Hello, RMI world!"));
			
//			Option 2
//			//Kontext setzen
//			Hashtable hashtable = new Hashtable(2);
//			hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
//			hashtable.put(Context.PROVIDER_URL, "rmi://localhost:1099/HelloServer");
//			InitialContext ictx = new InitialContext(hashtable);
//			// Remote-Objekte binden
//			ictx.bind("HelloServer", new Hello("Hello, RMI world!"));
			System.out.println("Hello Server is ready.");		
		} catch(Exception e) {
			System.out.println("Hello Server failed: " + e);
		}
	}
}