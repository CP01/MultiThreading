package charchit;

public class InterThreadComEvenOdd_2 {

	public static void main(String[] args) throws InterruptedException {

		// Approach One
//		Printer_2 printer_ = new Printer_2(1, 10);
	//	OddThread oddThread = new OddThread(printer_);
		//EvenThread evenThread = new EvenThread(printer_);

		System.out.println("Approach 2");
		
		// Approach 2
		Printer_2 printer = new Printer_2(1, 10);
		Thread odd = new Thread(new Runnable() {
			public void run() {
				for(int i=printer.getStart(); i<printer.getEnd();i+=2) {
					try {
						printer.printOdds(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread even = new Thread(() -> {
			for(int i = printer.getStart()+1; i<=printer.getEnd();i+=2) {
				try {
					printer.printEvens(i);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		odd.start();
		even.start();
		
	}
}

class Printer_2 {
	private int start, end;
	private boolean isEven = false;
	Printer_2(int x, int y) {
		start = x;
		end = y;
	}
	
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	
	public synchronized void printEvens(int x) throws InterruptedException {
		
		if(!isEven) {
			wait();
		}
		
		System.out.println(x);
		isEven = !isEven;
		notify();
	}
	
	public synchronized void printOdds(int x) throws InterruptedException {
		if(isEven) {
			wait();
		}
		
		System.out.println(x);
		isEven = !isEven;
		notify();
	}
}


// Approach One
class OddThread implements Runnable {
	
	Printer_2 printer;
	OddThread(Printer_2 printer) {
		this.printer = printer;
		Thread t = new Thread(this);
		t.start();
		
	}
	
	public void run() {
		for(int i=printer.getStart(); i<printer.getEnd();i+=2) {
			try {
				printer.printOdds(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class EvenThread implements Runnable {
	
	Printer_2 printer;
	EvenThread(Printer_2 printer) {
		this.printer = printer;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		for(int i = printer.getStart()+1; i<=printer.getEnd();i+=2) {
			try {
				printer.printEvens(i);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}