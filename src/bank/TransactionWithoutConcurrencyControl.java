package bank;

public class TransactionWithoutConcurrencyControl {

	Account ac;
	TransactionWithoutConcurrencyControl(Account ac) {
		this.ac = ac;
	}

	public int deposit(int amount) {
		ac.setBalance(ac.getBalance()+amount);
		System.out.println(Thread.currentThread() + " After Depositing "+ ac.acNo 
				+ " has "+ac.getBalance());
		return ac.getBalance();
	}

	public int withdraw(int amount) {
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

	public boolean transfer(Account to, int amount) {
		TransactionWithoutConcurrencyControl t = new TransactionWithoutConcurrencyControl(to);
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
