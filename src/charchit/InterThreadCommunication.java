package charchit;

public class InterThreadCommunication {

	public static void main(String[] args) throws InterruptedException {

		final Data d = new Data();
		final Producer p = new Producer(d);
		final Consumer c = new Consumer(d);
	}

}

class Data {
	int num;
	boolean isSet = false;

	public synchronized void getNum() throws InterruptedException {
		if (!isSet) {
			wait();
		}
		System.out.println("Get : " + num);
		isSet = false;
		notify();
		// return num;
	}

	public synchronized void setNum(int num) throws InterruptedException {
		if (isSet) {
			wait();
		}
		this.num = num;
		System.out.println("Set : " + num);
		isSet = true;
		notify();
	}

}

class Producer implements Runnable {
	Data d;

	Producer(Data d) {
		this.d = d;
		final Thread t1 = new Thread(this, "Producer");
		t1.start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				d.setNum(++i);
				// Thread.sleep(500);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	Data d;

	Consumer(Data d) {
		this.d = d;
		final Thread t2 = new Thread(this, "Consumer");
		t2.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				d.getNum();
				Thread.sleep(500);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}