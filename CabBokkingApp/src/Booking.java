import java.util.Date;


public class Booking {
	
	private String customerId;
	private String driverId;
	private CabBookingApp.CabType cabType;
	private Location pickUpLocation;
	private Location dropLocation;
	private double tripDistance;
	private double tripFare;
	private Payment.PaymentMode paymentMode;
	private Date date;
	
	
	public Booking(String customerId, String driverId, CabBookingApp.CabType cabType, Location pickUpLocation, Location dropLocation) {

		this.customerId = customerId;
		this.driverId = driverId;
		this.cabType = cabType;
		this.pickUpLocation = pickUpLocation;
		this.dropLocation = dropLocation;
		this.tripDistance = CabList.findDistance(pickUpLocation.getXCoordinate(), pickUpLocation.getYCoordinate(), dropLocation.getXCoordinate(), dropLocation.getYCoordinate());
		this.tripFare = calculateFare(tripDistance, driverId);
		this.paymentMode = findPaymentMode(customerId, this.tripFare);
		this.date = new Date();
	}
	
	
	private double calculateFare(double tripDistance, String driverId) {
		
		Cab allocatedCab = CabBookingApp.cabListObj.getCab(driverId);
		
		if(tripDistance - 10 > 0) {
			return allocatedCab.getCabTypeDetails().getCabBaseFare() + allocatedCab.getCabTypeDetails().getCabFarePerKM()*(tripDistance-10);
		}
		return allocatedCab.getCabTypeDetails().getCabBaseFare();
	}
	
	
	public double getTripFare() {
		return this.tripFare;
	}
	
	private Payment.PaymentMode findPaymentMode(String customerId, double tripFare) {
		Customer customer = CabBookingApp.customerListObj.getCustomer(customerId);
		
		if(customer.getAccount().getAccountBalance() >= tripFare) {
			return Payment.PaymentMode.WALLET_MONEY;
		}
		else if(customer.getAccount().getAccountBalance() > 0) {
			return Payment.PaymentMode.WALLET_MONEY_AND_CASH;
		}
		else {
			return Payment.PaymentMode.CASH;
		}
	}
	
	
	public Payment.PaymentMode getPaymentMode() {
		return this.paymentMode;
	}
	
	@Override
	public String toString() {
		return "Booking[\nCustomer Id = " + customerId + ",\nDriver Id = " + driverId + ",\nCab Type = " + cabType + ",\nPick Up Location = "
				+ pickUpLocation.toString() + ",\nDrop Location = " + dropLocation.toString() + ",\nTrip Distance = " + tripDistance + ",\nTrip Fare = " + tripFare + "\nPayment Mode = " + paymentMode + ",\nDate = " + date.toString()
				+ "\n]";
	}
	
	
	
}
