package newEvenOdd;

public class Runner {

	public static void main(String[] args) {

		int start = 2;
		int end = 10;
		final int evenStart, oddStart;
		
		if(start%2==0) {
			evenStart = start;
			oddStart = start+1;
		}
		else {
			evenStart = start+1;
			oddStart = start;
		}
		Printer p = new Printer(start);
		new Thread(() ->  { // Odd Thread
			for(int i=oddStart;i<=end;i+=2) {
				try {
					p.printOdd(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(() ->  { // Even Thread
			for(int i=evenStart;i<=end;i+=2) {
				try {
					p.printEven(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

}
