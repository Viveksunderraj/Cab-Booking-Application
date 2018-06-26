import java.util.ArrayList;

public class BookingList {
	
	private ArrayList<Booking> bookingList = new ArrayList<Booking>();
	
	public void addBooking(Booking newBooking) {
		synchronized (bookingList) {
		bookingList.add(newBooking);
		}
	}
}
