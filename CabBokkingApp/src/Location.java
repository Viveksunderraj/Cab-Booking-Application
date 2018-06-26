
public class Location {
	private String area = null;
	private double xCoordinate = 0, yCoordinate = 0;
	
	
	public Location(String area, double xCoordinate, double yCoordinate) {
		this.area = area;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	
	public String getArea() {
		return this.area;
	}
	
	public double getXCoordinate() {
		return this.xCoordinate;
	}
	
	public double getYCoordinate() {
		return this.yCoordinate;
	}
	
	@Override
	public String toString() {
		return "Location[\nArea = " + area + ",\nX Coordinate = " + xCoordinate + ",\nY Coordinate = " + yCoordinate + "\n]";
	}
	
	
	

}
