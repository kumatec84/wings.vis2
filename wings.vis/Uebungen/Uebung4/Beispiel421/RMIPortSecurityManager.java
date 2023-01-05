/*
 * Eigene Implementierung eines SecurityManagers. 
 * Der PortSecurityManager setzt eine eigene Policy-Datei, in der die geforderten Berechtiungen gesetzt werden.
 */
import java.rmi.*;

public class RMIPortSecurityManager extends RMISecurityManager {

	public RMIPortSecurityManager() {
		super();
		System.setProperty("java.security.policy", "ports.policy");
	}
}