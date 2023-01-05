import akka.actor.UntypedActor;

public class Printer extends UntypedActor {
	@Override
	public void onReceive(Object msg) {
		if (msg != null) {
			System.out.println("Nachricht an Printer: " + msg);
			getSender().tell(msg, getSelf());
		} else
			unhandled(msg);
	}
}
