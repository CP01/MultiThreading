package bank;

public class Account {

	int acNo;
	int balance = 0;
	User user;
	
	Account(int acNo, User user) {
		this.acNo = acNo;
		this.user = user;
	}
	
	public int getAcNo() {
		return acNo;
	}
	public void setAcNo(int acNo) {
		this.acNo = acNo;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
