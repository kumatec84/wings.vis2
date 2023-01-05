public class Applikation1 {

	public static void main(String[] args) {
		SleepingThread st1 = new SleepingThread();
		st1.start();
		SleepingThread st2 = new SleepingThread();
		st2.start();
	}
}
