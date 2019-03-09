package producerConsumer;

public class Runner {

	public static void main(String[] args) {

		Job job = new Job();
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					job.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					job.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		producer.start();
		consumer.start();
	}

}
