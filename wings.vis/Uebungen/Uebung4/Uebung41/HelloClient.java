import java.rmi.*;

public class HelloClient {
	public static void main(String[] args) {
		try {
			HelloInterface hello = (HelloInterface)Naming.lookup("rmi://rivera.wi.hs-wismar.de:8442/HelloServer");
			System.out.println(hello.sayHello());
		} catch(Exception e) {
			System.out.println("HelloClient exception: " + e);			
		}
	}
}