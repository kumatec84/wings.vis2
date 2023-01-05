import java.util.ArrayList;
import java.util.List;

public class Airline1 extends Airline {

	public Airline1() {
		super();				
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("ROM", "PARIS", "60"));
		flights.add(new Flight("PRAG", "WARSCHAU", "40"));
		flights.add(new Flight("BERLIN", "WIEN", "150"));
		flights.add(new Flight("RIGA", "KIEW", "45"));		
		setFlights(flights);		
	}
}