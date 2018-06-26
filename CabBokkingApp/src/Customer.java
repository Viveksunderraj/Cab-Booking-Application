
public class Customer {
	
	private String customerName;
	private String customerPhoneNumber;
	private Account zohoWallet;
	private String password;
	
	public Customer(String customerName, String customerPhoneNumber, String password, Account zohoWallet) {

		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.password = password;
		this.zohoWallet = zohoWallet;
	}
	
	public Account getAccount() {
		return this.zohoWallet;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String toString() {
		return "Customer[\nCustomer Name = " + customerName + ",\nCustomer Phone Number = " + customerPhoneNumber + "Zoho Wallet Balance = " + zohoWallet.getAccountBalance() + "\n]";
	}
}
