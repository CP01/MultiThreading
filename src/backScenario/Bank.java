package backScenario;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	
	private static Map<Integer,AtomicInteger> map = new HashMap<>();
	private static volatile Bank bank;
	private static ReentrantLock lock = new ReentrantLock();
	private Bank() {}
	
	public static Bank getBank() {
		if(bank==null) {
			synchronized (Bank.class){
				if(bank==null) {
					bank = new Bank();
				}
			}
		}
		return bank;
	}
	
	public boolean addAccount(int account) {
		if(!map.containsKey(account)) {
			map.put(account, new AtomicInteger(0));
			return true;
		}
		return false;
		
	}
	
	public static synchronized int deposit(int account, int amt) throws InterruptedException {
		if(!map.containsKey(account))
		{
			System.out.println("Invalid Account");
			return 0;
		}
		if(lock.tryLock(2, TimeUnit.SECONDS)) {
			Thread.sleep(1000);
			map.put(account, new AtomicInteger(map.get(account).addAndGet(amt)));
			lock.unlock();
		}
		return amt;
	}
	
	public static synchronized int withdraw(int account, int amt) throws InterruptedException {
		if(!map.containsKey(account))
		{
			System.out.println("Invalid Account");
			return 0;
		}
		if(lock.tryLock(2, TimeUnit.SECONDS)) {
		if(amt>map.get(account).intValue()) {
			System.out.println("Invalid Amount");
			return 0;
		}

		Thread.sleep(1000);
		map.put(account, new AtomicInteger(map.get(account).intValue() - amt));
		lock.unlock();
		}
		return amt;
	}
	
	public static synchronized void showBalance(int account) {
		System.out.println(account+" Account has : "+map.get(account));
	}

}
