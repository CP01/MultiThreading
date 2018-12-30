package charchit;

public class ThreadRunner {

	public static void main(String[] args) {

		ThreadMiddle t1 = new ThreadMiddle(10);
		ThreadMiddle t2 = new ThreadMiddle(30);
		t1.start();
		t2.start();
		
	}

}
