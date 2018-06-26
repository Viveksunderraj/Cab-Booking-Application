
public class ZohoCabs {
	
	private Account zohoBankAccount;
	private String password = "admin";
	public final static double OLA_SHARE_PERCENTAGE = 0.2;

	public ZohoCabs() {

		this.zohoBankAccount = new Account();
	}
	
	public Account getAccount() {
		return this.zohoBankAccount;
	}
	public boolean isPasswordMatch(String password) {
		
		if(this.password.equals(password)) {
			return true;
		}
		return false;
	}
}
