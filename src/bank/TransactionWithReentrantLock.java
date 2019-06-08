package bank;

import java.util.concurrent.locks.ReentrantLock;

public class TransactionWithReentrantLock {

	Account ac;
	ReentrantLock lock;
	TransactionWithReentrantLock(Account ac) {
		this.ac = ac;
		lock = new ReentrantLock();
	}

	public int deposit(int amount) {
		if(lock.isLocked()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("waiting for lock in deposit for account "+this.ac.acNo);
		}
		System.out.println("Locking in deposit for account "+this.ac.acNo);
		lock.lock();
		ac.setBalance(ac.getBalance()+amount);
		System.out.println(Thread.currentThread() + " After Depositing "+ ac.acNo 
				+ " has "+ac.getBalance());
		lock.unlock();
		return ac.getBalance();
	}

	public int withdraw(int amount) {
		if(lock.isLocked()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("waiting for lock in withdraw for account "+this.ac.acNo);
		}
		System.out.println("Locking in withdraw for account "+this.ac.acNo);
		lock.lock();
		if(ac.getBalance()>=amount) {
			ac.setBalance(ac.getBalance()-amount);
			System.out.println(Thread.currentThread() + " After Withdrawing "+ ac.acNo 
					+ " has "+ac.getBalance());
			lock.unlock();
		}
		else {
			throw new RuntimeException("Insufficient Balance!!!");
		}
		return ac.getBalance();
	}

	public boolean transfer(Account to, int amount) {
		if(lock.isLocked()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("waiting for lock in transfer for account "+this.ac.acNo);
		}
		TransactionWithReentrantLock t = new TransactionWithReentrantLock(to);
		System.out.println("Locking in transfer for account "+this.ac.acNo);
		lock.lock();
		if(ac.getBalance()>=amount) {
			System.out.println("***Transfering start***");
			this.withdraw(amount);
			try {
				t.deposit(amount);
			}
			catch(Exception e) {
				System.out.println(e);
				System.out.println("Failed to deposit in transferred account "+t.ac.getAcNo());
				System.out.println("Depositing back amount to "+this.ac.getAcNo());
				this.deposit(amount);
			}
			finally {
				lock.unlock();
			}
			System.out.println("***Transfering done***");
			return true;
		}
		else {
			throw new RuntimeException("Insufficient Balance!!!");
		}
	}
}
