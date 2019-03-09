package producerConsumer;

public class Job {
	int x = 1;
	boolean isProduced = false;

	public void produce() throws InterruptedException {

		while(x!=10) {
			synchronized (this) {

				while(isProduced) {
					wait();
				}

				System.out.println("Produced "+x);
				isProduced = true;
				notify();
				Thread.sleep(500);
			}
		}
	}

	public void consume() throws InterruptedException {

		while(x!=11) {
			synchronized (this) {

				while(!isProduced) {
					wait();
				}

				System.out.println("Consumed "+x);
				isProduced = false;
				x++;
				notify();
				Thread.sleep(500);
			}
		}
	}

}
