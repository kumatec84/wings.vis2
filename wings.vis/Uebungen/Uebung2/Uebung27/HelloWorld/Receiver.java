import akka.actor.UntypedActor;

public class Receiver extends UntypedActor {
	@Override
	public void onReceive(Object msg) {
		if (msg != null) {
			System.out.println(msg + "(empfangen und ausgegeben von Receiver) |");
		} else
			unhandled(msg);
	}
}
