public class ThreadPrio extends Thread {
	String name;

	public ThreadPrio(String pName) {
		name = pName;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(10000);
				System.out.println(name + " " + i);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}