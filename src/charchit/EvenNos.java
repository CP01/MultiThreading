package charchit;

public class EvenNos implements Runnable {

    private final String threadName;

    public EvenNos(String threadName) {
	this.threadName = threadName;
    }

    @Override
    public void run() {

	try {
	    for (int i = 0; i <= 10; i += 2) {
		System.out.println(Thread.currentThread().getName() + " " + i);
		Thread.sleep(1000);
	    }
	} catch (final InterruptedException e) {
	    e.printStackTrace();
	}

	/*
	 * for (int i = 0; i <= 10; i += 2) {
	 * print(i);
	 * }
	 */
    }

    public String getThreadName() {
	return threadName;
    }

    synchronized void print(int n) {
	System.out.println(Thread.currentThread().getName() + " " + n);
	try {
	    notifyAll();
	    wait();
	} catch (final InterruptedException e) {
	    e.printStackTrace();
	}
    }

}