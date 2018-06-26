
public class CabTypeDetails {
	
	private CabBookingApp.CabType cabType;
	private final double BASE_FARE;
	private final double FARE_PER_KM;
	private final int NUMBER_OF_SEATS;
	
	
	public CabTypeDetails(CabBookingApp.CabType cabType, double baseFare, double farePerKM, int numberOfSeats) {

		this.cabType = cabType;
		this.BASE_FARE = baseFare;
		this.FARE_PER_KM = farePerKM;
		this.NUMBER_OF_SEATS = numberOfSeats;
	}


	protected CabBookingApp.CabType getCabType() {
		return this.cabType;
	}
	
	protected double getCabBaseFare() {
		return this.BASE_FARE;
	}
	
	protected double getCabFarePerKM() {
		return this.FARE_PER_KM;
	}
	
	
	@Override
	public String toString() {
		return "CabTypeDetails [Type = " + cabType + ", Base Fare for 10 km = " + BASE_FARE + ", Fare Per km = " + FARE_PER_KM
				+ ", Number Of Seats = " + NUMBER_OF_SEATS + "]";
	}
}
