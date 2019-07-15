package newEvenOdd;

public class Printer {

	int start = 1;
	boolean isOddTurn = true;
	
	Printer() {}
	Printer(int start) {
		this.start = start;
		if(start%2==0) {
			isOddTurn = !isOddTurn;
		}
	}
	
	public synchronized void printEven(int x) throws InterruptedException {
		while(isOddTurn) {
			wait();
		}
		
		System.out.println(x);
		isOddTurn = !isOddTurn;
		notify();
	}
	
	public synchronized void printOdd(int x) throws InterruptedException {
		while(!isOddTurn) {
			wait();
		}
		
		System.out.print(x+ " ");
		isOddTurn = !isOddTurn;
		notify();
	}
}
