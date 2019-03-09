package backScenario;

public class Transaction {

	public static void main(String[] args) {

		Bank bank = Bank.getBank();
		bank.addAccount(1);
		bank.addAccount(2);
		bank.addAccount(3);
		bank.addAccount(4);
		Thread t1 = new Thread(()-> {
			for(int i=1;i<=1;i++) {
				try {
					bank.deposit(i, 100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.print(Thread.currentThread());
				bank.showBalance(i);
			}
		});

		Thread t2 = new Thread(()-> {
			for(int i=1;i<=1;i++) {
				try {
					bank.deposit(i, 100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.print(Thread.currentThread());
				bank.showBalance(i);
			}
		});

		Thread t3 = new Thread(()-> {
			for(int i=1;i<=1;i++) {
				try {
					bank.withdraw(i, 150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.print(Thread.currentThread());
				bank.showBalance(i);
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}

}
