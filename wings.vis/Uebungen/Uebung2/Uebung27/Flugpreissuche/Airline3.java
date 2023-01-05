import java.util.ArrayList;
import java.util.List;

public class Airline3 extends Airline {

	public Airline3() {
		super();
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("ROM", "PARIS", "70"));
		flights.add(new Flight("PRAG", "MINSK", "47"));
		flights.add(new Flight("BERLIN", "WIEN", "70"));
		flights.add(new Flight("RIGA", "KIEW", "47"));
		flights.add(new Flight("PRAG", "WARSCHAU", "70"));
		setFlights(flights);
	}
}