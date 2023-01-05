import java.rmi.*;

public class HelloClient {
	public static void main(String[] args) {
		System.setSecurityManager(new RMIPortSecurityManager());
		try {
			HelloInterface hello = (HelloInterface)Naming.lookup("rmi://localhost:1099/HelloServer");
			System.out.println(hello.sayHello());
		} catch(Exception e) {
			System.out.println("HelloClient exception: " + e);			
		}
	}
}