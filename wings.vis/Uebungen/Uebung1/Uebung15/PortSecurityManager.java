/*
 * Eigene Implementierung eines SecurityManagers. 
 * Der PortSecurityManager setzt eine eigene Policy-Datei, in der die geforderten Berechtiungen gesetzt werden.
 */
public class PortSecurityManager extends SecurityManager {

	public PortSecurityManager() {
		super();
		System.setProperty("java.security.policy", "ports.policy");
	}
}