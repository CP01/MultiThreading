package bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobDistributor {

	static ExecutorService es;
	static {
		es = Executors.newFixedThreadPool(3);
	}
	public void distribute(TransactionWithReentrantLock tx, String operation, int amt, Account transferTo) {
		switch(operation) {
		case "deposit":
		{
			es.execute(() -> {
				tx.deposit(amt);
			});
			break;
		}
		case "withdraw" :
		{
			es.execute(() -> {
				tx.withdraw(amt);
			});
			break;
		}
		case "transer" :
		{
			es.execute(() -> {
				tx.transfer(transferTo, amt);
			});
		}
		}
	}
}
