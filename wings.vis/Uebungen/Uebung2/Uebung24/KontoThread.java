public class KontoThread extends Thread {
	private Konto konto;
	private String person;
	private int betrag;

	public KontoThread(String person, Konto konto, int betrag) {
		this.konto = konto;
		this.person = person;
		this.betrag = betrag;
	}

	public void run() {
		konto.buchung(person, getId() + "", betrag);
		konto.buchung(person, getId() + "", betrag);
		konto.buchung(person, getId() + "", betrag);
	}

	public Konto getKonto() {
		return konto;
	}

	public void setKonto(Konto konto) {
		this.konto = konto;
	}
}