package charchit;

public class ThreadHandler {

    public static void main(String[] args) throws InterruptedException {

	final OddNos odd = new OddNos("Odd");
	final Thread even = new Thread(new EvenNos("Even"), "Even");
	even.start();
	even.sleep(10);
	odd.start();
    }

}