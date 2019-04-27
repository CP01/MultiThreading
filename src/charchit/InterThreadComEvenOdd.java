package charchit;

public class InterThreadComEvenOdd {

	public static void main(String[] args) throws InterruptedException {

		final Printer d = new Printer();
		final Even p = new Even(d);
		final Odd c = new Odd(d);
	}

}

class Printer {
	int limit = 30;
	boolean isEven = false;

	public synchronized void printEven(int even) throws InterruptedException {
		if (!isEven) {
			wait();
		}
		System.out.println("Even : " + even);
		isEven = false;
		notify();
		// return num;
	}

	public synchronized void printOdd(int odd) throws InterruptedException {
		if (isEven) {
			wait();
		}
		//this.num = odd;
		System.out.println("Odd : " + odd);
		isEven = true;
		notify();
	}

}

class Even implements Runnable {
	Printer d;

	Even(Printer d) {
		this.d = d;
		final Thread t1 = new Thread(this, "Producer");
		t1.start();
	}

	@Override
	public void run() {
		int i = 2;
		while (i<=d.limit) {
			try {
				d.printEven(i);
				i += 2;
				// Thread.sleep(500);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Odd implements Runnable {
	Printer d;

	Odd(Printer d) {
		this.d = d;
		final Thread t2 = new Thread(this, "Consumer");
		t2.start();
	}

	@Override
	public void run() {
		int i = 1;
		while (i<=d.limit) {
			try {
				d.printOdd(i);
				i += 2;
				Thread.sleep(500);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}