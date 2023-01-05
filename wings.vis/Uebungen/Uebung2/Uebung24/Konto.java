public class Konto {
	private int kontostand;

	public Konto(int kontostand) {
		this.kontostand = kontostand;
	}

	public synchronized void buchung(String person, String transaktionsNr, int betrag) {
		System.out.println("[" + person + "] " + " Kontostand vor der Buchung: " + getKontostand());
		System.out.println("[" + person + "] Buchungsbetrag: " + betrag);
		setKontostand(getKontostand() + betrag);
		System.out.println("[" + person + "] Kontostand nach der Buchung: " + getKontostand());
	}

	public synchronized int getKontostand() {
		return kontostand;
	}

	public synchronized void setKontostand(int kontostand) {
		this.kontostand = kontostand;
	}
}