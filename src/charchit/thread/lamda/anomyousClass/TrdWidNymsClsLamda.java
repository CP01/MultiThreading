package charchit.thread.lamda.anomyousClass;

public class TrdWidNymsClsLamda {

    public static void main(String args[]) {
	final Thread t1 = new Thread(new Runnable() { // Anonymous Class Example
	    @Override
	    public void run() {
		for (int i = 0; i <= 10; i += 2) {
		    System.out.println(Thread.currentThread().getName() + " " + i);
		    try {
			Thread.sleep(1000);
		    } catch (final InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	}, "Even Thread");

	final Thread t2 = new Thread(() -> { // Lambda Function Example
	    for (int i = 1; i <= 10; i += 2) {
		System.out.println(Thread.currentThread().getName() + " " + i);
		try {
		    Thread.sleep(1000);
		} catch (final InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}, "Odd Thread");

	t1.start();
	try {
	    t1.sleep(10);
	    t2.start();
	    System.out.println("t1 alive " + t1.isAlive());
	    t1.join(); // wait here till t1 joins main thread
	    System.out.println("t1 alive " + t1.isAlive());

	} catch (final InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("End of execution");
    }
}
