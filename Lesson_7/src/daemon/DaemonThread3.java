package daemon;
/**
 * A “daemon” thread is one that is supposed to provide a general service 
 * in the background as long as the program is running, but is not part of the essence 
 * of the program. 
 * Thus, when all of the non-daemon threads complete, the program is terminated. Conversely, 
 * if there are any non-daemon threads still running, the program doesn’t terminate. 
 * There is, for instance, a non-daemon thread that runs main( ). 
	1. A daemon thread is a background thread.
	2. It is subordinate to the thread that creates it.
	3. When the thread that created the daemon thread ends, the daemon thread dies with it.
	4. A thread must be marked as a daemon thread before it is started
	5. finally blocks are not executed, 
    6. Stacks are not unwound - the JVM just exits.
    
    Read more: 
    http://javarevisited.blogspot.com/2012/03/what-is-daemon-thread-in-java-and.html#ixzz2HUcdBbH1

 */
class MyDaemonThread extends Thread{
	
	public MyDaemonThread(String name){
		super(name);
	}
	public void run(){
		boolean b = true;
		while (b){
			System.out.println(currentThread().getName());
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(currentThread().getName() + " terminated");
	}
}
class RegularThread extends Thread{
	
	public RegularThread(String name){
		super(name);
	}
	public void run(){
		int i=0;
		while (i++<3){
			System.out.println("i = "+i + ", "+currentThread().getName());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(currentThread().getName() + "  terminated");
	}
}

public class DaemonThread3 {
	public static void main(String[] args) {
		MyDaemonThread daemon = new MyDaemonThread("MyDaemonThread");
		daemon.setDaemon(true);
		daemon.start();
		RegularThread r1 = new RegularThread("R1");
		RegularThread r2 = new RegularThread("R2");
		RegularThread r3 = new RegularThread("R3");
		r1.start();
		r2.start();
		r3.start();
		System.out.println("daemon.isAlive()? " + daemon.isAlive());
		try {
			r1.join();
			r2.join();
			r3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("daemon.isAlive()? " + daemon.isAlive());
		System.out.println("main  terminated");
	}

}
