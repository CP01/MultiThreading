package charchit;

public class SynchronizeDemo {

    static int count;

    public static void main(String[] args) throws InterruptedException {

	final Thread t1 = getNewThread();
	final Thread t2 = getNewThread();
	t1.start();
	t2.start();
	t1.join();
	// t2.join();
	System.out.println(count);

    }

    // remove synchronized keyword and check the impact of it
    public static synchronized void increment() {
	count++;
    }

    public static Thread getNewThread() {
	return new Thread(() -> {
	    for (int i = 0; i < 1000; i++) {
		increment();
	    }
	});
    }
}
