package Volatile;

public class VTest extends Thread{
	volatile boolean keepRunning = true;
	//boolean keepRunning = true;
	public void run() {
		while (keepRunning) {
			int x = 1;
		}
		System.out.println("Thread terminated.");
	}

	public static void main(String[] args) throws InterruptedException {
		VTest t = new VTest();
		t.start();
		Thread.sleep(1000);
		t.keepRunning = false;
		System.out.println("keepRunning set to false.");
	}

}
