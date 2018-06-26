
public class Cab {
	
	private String driverName;
	private String cabNumberPlate;
	private String driverPhoneNumber;
	private Location location;
	private Account driverAccount;
	private String password;
	private boolean isAvailable;
	private CabTypeDetails cabType;
	public final static double DRIVER_SHARE_PERCENTAGE = 0.8;
	
	

	public Cab(String driverName, String cabNumberPlate, String driverPhoneNumber, CabTypeDetails cabType, String password, Account newAccount) {

		this.driverName = driverName;
		this.cabNumberPlate = cabNumberPlate;
		this.driverPhoneNumber = driverPhoneNumber;
		this.cabType = cabType;
		this.password = password;
		this.isAvailable = false;
		this.location = null;
		this.driverAccount = newAccount;
	}
	
	
	public String getPassword() {
		return this.password;
	}
	
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setStatus(boolean status) {
		this.isAvailable = status;
	}
	
	public boolean getStatus() {
		return this.isAvailable;
	}
	
	public CabTypeDetails getCabTypeDetails() {
		return this.cabType;
	}
	
	
	public Account getAccount() {
		return this.driverAccount;
	}
	
	public String displayLocation() {
		return "Cab[\nDriver Name = " + driverName + ",\nPhone Number = " 
	    + driverPhoneNumber + "\nLocation = " + location.toString() + "\n]";
	}
	
	
	@Override
	public String toString() {
		return "Cab[\nDriver Name = " + driverName + ",\nNumber Plate = " + cabNumberPlate + ",\nPhone Number = "
				+ driverPhoneNumber + ",\nCab Type = " + cabType.toString() + ",\nCab Available = "
				+ isAvailable + "\n]";
	}
}
