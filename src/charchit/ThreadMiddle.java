package charchit;

public class ThreadMiddle extends Thread{
	int var;
	
	ThreadMiddle(int x)
	{
		var = x;
	}

	@Override
	public void run() {

		try {
			for (int i = var; i <= var+10; i += 1) {
				System.out.println(ThreadStaticTest.getStaticInt() + " B4 " + Thread.currentThread().getName());
				ThreadStaticTest.setStaticInt(i);
				System.out.println(ThreadStaticTest.getStaticInt() + " After " + Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
