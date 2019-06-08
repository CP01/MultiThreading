package bank;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRunner {

	public static void main(String[] args) {
		for(int i=0;i<1;i++) {
			System.out.println(i);
			User A = new User(1, "A");
			User B = new User(2, "B");
			Account a1 = new Account(101, A);
			Account a2 = new Account(102, B);
			Account a3 = new Account(103, B);

			HashMap<Integer, Account> accounts = new HashMap<>();
			accounts.put(a1.getAcNo(), a1);
			accounts.put(a2.getAcNo(), a2);
			accounts.put(a3.getAcNo(), a3);

			TransactionWithReentrantLock tx = new TransactionWithReentrantLock(a1);
			// TransactionWithSync tx = new TransactionWithSync(a1);
			// TransactionWithoutConcurrencyControl tx = new TransactionWithoutConcurrencyControl(a1);
			// In without concurrency control - some of times final bal of 101 comes as 110 and of 102 as 20

			ExecutorService es = Executors.newFixedThreadPool(3);
			es.execute(() ->  {
				tx.deposit(100);
			});

			es.execute(() ->  {
				tx.deposit(50);
			});

			es.execute(() ->  {
				tx.deposit(10);
			});

			es.execute(() ->  {
				tx.withdraw(10);
			});

			es.execute(() ->  {
				tx.withdraw(10);
			});

			es.execute(() ->  {
				tx.withdraw(10);
			});

			es.execute(() ->  {
				tx.transfer(a2, 20);
			});

			es.execute(() ->  {
				tx.transfer(a2, 10);
			});
			
			es.shutdown();
			
			/*Thread deposit = new Thread(() ->  {
				tx.deposit(100);
			});

			Thread deposit2 = new Thread(() ->  {
				tx.deposit(50);
			});

			Thread deposit3 = new Thread(() ->  {
				tx.deposit(10);
			});

			Thread withdraw = new Thread(() ->  {
				tx.withdraw(10);
			});

			Thread withdraw2 = new Thread(() ->  {
				tx.withdraw(10);
			});

			Thread withdraw3 = new Thread(() ->  {
				tx.withdraw(10);
			});

			Thread transfer = new Thread(() ->  {
				tx.transfer(a2, 20);
			});

			Thread transfer2 = new Thread(() ->  {
				tx.transfer(a2, 10);
			});

			deposit.start();


			try { 
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}


			withdraw.start();
			withdraw2.start();
			deposit2.start();
			transfer.start();
			transfer2.start();
			deposit3.start();
			withdraw3.start();*/

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showFinalBal(accounts);
			/*if(accounts.get(101).balance != 100 || accounts.get(102).getBalance()!=30) {
				System.out.println("--- Invalid Result ---");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}*/
		}
		//System.out.println("SuccesS");
	}

	public static void showFinalBal(HashMap<Integer, Account> accounts) {
		System.out.println("User - AcNo - Amount");
		for(Account ac : accounts.values()) {
			System.out.println(ac.getUser().getName() + " " + ac.getAcNo() + " " + ac.getBalance());
		}
	}


}
