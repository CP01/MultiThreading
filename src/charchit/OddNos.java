package charchit;

public class OddNos extends Thread {

    public OddNos(String threadName) {
	super(threadName);
    }

    @Override
    public void run() {

	try {
	    for (int i = 1; i <= 10; i += 2) {
		System.out.println(Thread.currentThread().getName() + " " + i);
		Thread.sleep(1000);
	    }
	} catch (final InterruptedException e) {
	    e.printStackTrace();
	}

	/*
	 * for (int i = 1; i <= 10; i += 2) {
	 * print(i);
	 * }
	 */
    }

    synchronized void print(int n) {
	System.out.println(Thread.currentThread().getName() + " " + n);
	try {
	    notifyAll();
	    wait();
	} catch (final InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}