import java.util.HashMap;
import java.util.Map.Entry;


public class CabList {
	
	private HashMap<String ,Cab> driverList = new HashMap<String, Cab>();
	
	public void addCab(String key,Cab newCab) {
		
		synchronized(driverList) {
		
			driverList.put(key,newCab);
		}
	}
	
	public Cab getCab(String key) {
		
		synchronized (driverList) {
			
		Cab cab = driverList.get(key);
		
		if(cab != null) {
			return cab;
		}
		return null;
		}
	}
	
	public boolean isDriverExists(String key) {
		
		if(driverList.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	public boolean isPasswordMatch(String key, String password) {
		Cab driver = getCab(key);
		
		if(driver.getPassword().equals(password)) {
			return true;
		}
		
		return false;
	}
	
	public void updateCabStatus(Cab cab, boolean status) {
		Cab driver = cab;
		
		driver.setStatus(status);
		//System.out.println(driver.toString());
	}
	
	public void updateCabLocation(Cab cab, Location location) {
		Cab driver = cab;
		
		driver.setLocation(location);
		//System.out.println(driver.toString());
	}
	
	public static double findDistance(double x1, double y1, double x2, double y2) {
		
		return  Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
		
	}
	
	public synchronized String findNearestCab(CabBookingApp.CabType cabType, Location pickUpLocation ) {
		
		String driverId = null;
		double minDistance = Double.MAX_VALUE;
		//System.out.println("IN FIND NEAREST CAB");
		
		for(Entry<String, Cab> entry:driverList.entrySet()) {
			
			Cab cabObj = entry.getValue();
			
			if(cabObj.getStatus() == true) {
				
				if(cabObj.getCabTypeDetails().getCabType() == cabType) {
					
					double distance = findDistance(pickUpLocation.getXCoordinate(), pickUpLocation.getYCoordinate(), cabObj.getLocation().getXCoordinate(), cabObj.getLocation().getYCoordinate());
					
					if(distance <= 10 && distance < minDistance) {
						minDistance = distance;
						driverId = entry.getKey();
					}	
				}	
			}
		}
		
		if(driverId == null) {
			return null;
		}
		return driverId;
	}

}
