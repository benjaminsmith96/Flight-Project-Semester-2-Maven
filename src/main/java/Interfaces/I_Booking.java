package Interfaces;

//import Booking.Booking;
import java.sql.SQLException;
import BookingBuilder.Booking;
import Flights.Airline;
import Flights.Flight;
import java.util.ArrayList;
import java.util.Date;
import Users.Customer;

public interface I_Booking {

	/**
	 * Create a new booking
	 * @param customer
	 * @param flight
	 * @param baggage
	 * @param type
	 * @return Booking
	 */
	public Booking makeBooking(Customer customer, Airline flight, int baggage, String type, Date date);

	/**
	 * Calculate the booking price
	 * @param flight
	 * @param type
	 * @param baggage
	 * @return price
	 */
	public float calculatePrice(Airline flight, String type, int baggage);

	/**
	 * Inserts a booking into the database
	 * @param booking
	 * @throws SQLException
	 * @throws Exception
	 */
	public void insertBooking(Booking booking) throws SQLException, Exception;

	/**
	 * If the user decide to have a discount this method update the price of the booking
	 * @param booking
	 * @param price
	 */
	public void changePrice(Booking booking, float price);
        
        public ArrayList<Booking> getUserBookings(Customer cust);
        
        public ArrayList<Booking> getAllUserBookings();
        
        public void cancelFlight(int flightID, Booking book);
        
        public Booking createBooking(int bookingID, Customer cust);

}
