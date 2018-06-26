
public class Payment {
	
	public enum PaymentMode {
		WALLET_MONEY, CASH, WALLET_MONEY_AND_CASH;
	}
	
	public void payThroughZohoWallet(String customerId, String DriverId, double tripFare) {
		
		Customer customer = CabBookingApp.customerListObj.getCustomer(customerId);
		Cab driver = CabBookingApp.cabListObj.getCab(DriverId);
		
		customer.getAccount().withdrawAmount(tripFare);
		
		driver.getAccount().depositAmount(tripFare*(Cab.DRIVER_SHARE_PERCENTAGE));
		
		CabBookingApp.zohoCabsObj.getAccount().depositAmount(tripFare*(ZohoCabs.OLA_SHARE_PERCENTAGE));
		
		
	}
	
	public void payThroughCash(String customerId, String DriverId, double tripFare) {
		
		Cab driver = CabBookingApp.cabListObj.getCab(DriverId);
		
		driver.getAccount().withdrawAmount(tripFare*(ZohoCabs.OLA_SHARE_PERCENTAGE));
		
		CabBookingApp.zohoCabsObj.getAccount().depositAmount(tripFare*(ZohoCabs.OLA_SHARE_PERCENTAGE));
	}

}
