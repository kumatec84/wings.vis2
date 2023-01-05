import java.util.HashMap;
import java.util.List;
import java.util.Map;
import akka.actor.UntypedActor;

public class Airline extends UntypedActor {

	private Map<String, Flight> flights;

	public Map<String, Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = new HashMap<String, Flight>();
		for (Flight flight : flights) {
			this.flights.put(flight.getFrom() + "-" + flight.getTo(), flight);
		}
	}

	@Override
	public void onReceive(Object msg) {
		if (msg != null) {
			Flight flightResponse = null;
			try {
				Flight flightRequest = (Flight) msg;
				flightResponse = flights.get(flightRequest.getFrom() + "-" + flightRequest.getTo());
				if (flightResponse == null) {
					flightResponse = new Flight(flightRequest.getFrom(), flightRequest.getTo(), null);
				}
				getSender().tell(flightResponse, getSelf());
				getContext().stop(getSelf());
			} catch (ClassCastException e) {
				getSender().tell(msg, getSelf());
			}
		} else
			unhandled(msg);
	}
}