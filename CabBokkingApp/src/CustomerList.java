import java.util.HashMap;

public class CustomerList {
	
	private HashMap<String ,Customer> customerList = new HashMap<String, Customer>();
	
	
	public void addCustomer(String key, Customer newCustomer) {
		
		synchronized (customerList) {
			customerList.put(key, newCustomer);
		}
	}
	
	public Customer getCustomer(String key) {
		synchronized (customerList) {
			
		Customer customer = customerList.get(key);
		
		if(customer != null) {
			return customer;
		}
		return null;
		}
	}
	
	
	public boolean isCustomerExists(String key) {
		
		if(customerList.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	public boolean isPasswordMatch(String key, String password) {
		Customer customer = getCustomer(key);
		
		if(customer.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public void depositMoneyToWallet(Customer customer, double money) {
		
		customer.getAccount().depositAmount(money);
		
	}
	
}

