import java.util.Scanner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class BookingSite extends UntypedActor {

	// javac -classpath '*' ./*.java -d classes && jar -cvf Flugpreissuche.jar -C classes/ . && clear && java -classpath "*" akka.Main BookingSite

	@Override
	public void preStart() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ihre Flugpreissuche");
		System.out.println("===================");
		System.out.print("Start: ");
		String from = sc.nextLine();
		System.out.print("Ziel: ");
		String to = sc.nextLine();
		System.out.println("");
		System.out.println("Suchergebnisse: ");
		System.out.println("===============");
		sc.close();
		Flight flight = new Flight(from.toUpperCase(), to.toUpperCase(), null);
		ActorSystem system = ActorSystem.create("test-system");
		final ActorRef airline1 = system.actorOf(Props.create(Airline1.class), "Airline1");
		final ActorRef airline2 = system.actorOf(Props.create(Airline2.class), "Airline2");
		final ActorRef airline3 = system.actorOf(Props.create(Airline3.class), "Airline3");
		airline1.tell(flight, getSelf());
		airline2.tell(flight, getSelf());
		airline3.tell(flight, getSelf());
		// system.stop(getSelf());
		// system.terminate();
	}

	@Override
	public void onReceive(Object msg) {
		if (msg != null) {
			Flight flight = null;
			try {
				flight = Flight.class.cast(msg);
				if (flight.getPrice() != null) {
					System.out.println(getSender().path().name() + ": Flug " + flight.getFrom() + " - " + flight.getTo()
							+ " kostet " + flight.getPrice() + " EURO.");
				} else {
					System.out.println(getSender().path().name() + ": Der Flug " + flight.getFrom() + " - "
							+ flight.getTo() + " wird nicht angeboten.");
				}
			} catch (ClassCastException e) {
				System.err.println("Fehler: " + e.getMessage());
			}
		} else {
			unhandled(msg);
		}
	}
}