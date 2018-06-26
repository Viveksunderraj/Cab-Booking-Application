
public class Account {
	
	private double accountBalance;
	
	
	
	public Account() {
		this.accountBalance = 0;
	}

	public void depositAmount(double amount) {
		this.accountBalance += amount;
	}
	
	public void withdrawAmount(double amount) {
		this.accountBalance -= amount;
	}
	
	public double getAccountBalance() {
		return this.accountBalance;
	}
	

}
