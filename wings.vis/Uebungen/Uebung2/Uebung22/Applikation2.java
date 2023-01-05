public class Applikation2 {

	public static void main(String[] args) {
		SleepingThread st2 = new SleepingThread();
		st2.setPriority(Thread.MIN_PRIORITY);
		st2.setName("Thread_MIN_PRIORITY");
		st2.start();
		
		SleepingThread st1 = new SleepingThread();
		st1.setPriority(Thread.MAX_PRIORITY);
		st1.setName("Thread_MAX_PRIORITY");
		st1.start();

		SleepingThread st3 = new SleepingThread();
		st3.setPriority(Thread.NORM_PRIORITY);
		st3.setName("Thread_NORM_PRIORITY");
		st3.start();
	}
}