import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class PrinterApp extends UntypedActor {

	@Override
	public void preStart() {
		ActorSystem system = ActorSystem.create("test-system");
		final ActorRef printer = system.actorOf(Props.create(Printer.class), "printer");
		printer.tell("Hello, world...!", getSelf());
		system.stop(printer);
		system.stop(getSelf());
		system.terminate();
	}
	
	@Override
	public void onReceive(Object msg) {
		// TODO;
	}
}