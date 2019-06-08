package bank;

public class TransactionWithSync {

	Account ac;
	TransactionWithSync(Account ac) {
		this.ac = ac;
	}

	public synchronized int deposit(int amount) {
		ac.setBalance(ac.getBalance()+amount);
		System.out.println(Thread.currentThread() + " After Depositing "+ ac.acNo 
				+ " has "+ac.getBalance());
		return ac.getBalance();
	}

	public synchronized int withdraw(int amount) {
		if(ac.getBalance()>=amount) {
			ac.setBalance(ac.getBalance()-amount);
			System.out.println(Thread.currentThread() + " After Withdrawing "+ ac.acNo 
					+ " has "+ac.getBalance());
		}
		else {
			throw new RuntimeException("Insufficient Balance!!!");
		}
		return ac.getBalance();
	}

	public synchronized boolean transfer(Account to, int amount) {
		TransactionWithSync t = new TransactionWithSync(to);
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
			System.out.println("***Transfering done***");
			return true;
		}
		else {
			throw new RuntimeException("Insufficient Balance!!!");
		}
	}
}
