public class Anwendung {
	public static void main(String[] args) {
		Konto konto1 = new Konto(100);
		KontoThread t1 = new KontoThread("Firma", konto1, 1000); 
		KontoThread t2 = new KontoThread("Oma", konto1, 50);
		KontoThread t3 = new KontoThread("Ich", konto1, -250); 
		t1.start();
		t2.start();
		t3.start();
	}
}