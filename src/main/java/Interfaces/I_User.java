package Interfaces;

//import Booking.Booking;
import BookingBuilder.*;
import java.sql.SQLException;
import java.util.ArrayList;
import Users.Customer;
import Users.User;

public interface I_User {

	/**
	 * Creates a new user
	 * @param username
	 * @param password
	 * @throws SQLException
	 * @throws Exception
	 */
	public void createUser(String username, String password) throws SQLException, Exception;

	/**
	 * Display the list of available flights
	 * @throws Exception
	 */
	public void viewFlights() throws Exception ;

	/**
	 * Display and return the user details
	 * @param user
	 * @return
	 */
        public String getUserDetails(User user);
        
        public ArrayList<Customer> getUserList();
        
        public ArrayList<Booking> getSalesList();
        
        public void removeUser(int userID);

}
