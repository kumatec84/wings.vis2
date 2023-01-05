import java.util.ArrayList;
import java.util.List;

public class Airline2 extends Airline {

	public Airline2() {
		super();				
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("ROM", "PARIS", "66"));
		flights.add(new Flight("PRAG", "WARSCHAU", "44"));
		flights.add(new Flight("BERLIN", "STUTTGART", "155"));
		flights.add(new Flight("RIGA", "KIEW", "40"));		
		setFlights(flights);		
	}
}