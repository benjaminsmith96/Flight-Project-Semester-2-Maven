package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BookingBuilder.Booking;
import Flights.Airline;
import Flights.Flight;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import Users.Customer;
import Users.User;

public class BookingDBController {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;
        private ResultSet resultSet2 = null;
        
         public ArrayList<Booking> getFullBookingList() throws Exception {

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                int bookingID;
                int flightNumber;
                int userID;
                String classType;
                int baggage;
                float price;
		FlightDBController db1 = new FlightDBController();
                UserDBController db2 = new UserDBController();
                User user = new Customer();
                
                Calendar cal = Calendar.getInstance();
                Date date=cal.getTime();
                
                Airline flight;
		ArrayList<Booking> bookList = new ArrayList<Booking>();
                //Booking userBook = new Booking();

		preparedStatement = connect.prepareStatement("SELECT * FROM abcs.flight_bookings;");
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			bookingID = resultSet.getInt("booking_id");
			flightNumber = resultSet.getInt("flight_number");
			classType = resultSet.getString("class");
			baggage = resultSet.getInt("baggage");
                        userID = resultSet.getInt("user_id");
			price = resultSet.getFloat("booking_price");
                        flight = db1.createFlight(flightNumber);
                        user = db2.getUser(userID);
                        Booking b =  new Booking.BBuilder(bookingID, (Customer) user, flight, baggage, classType, date).build();
                        
                        //userBook = new Booking(bookingID, (Customer) user, flight, baggage, classType, price, date);
                        bookList.add(b);
                       
		}

		db.close();

		

		return bookList;
	}

	public void insertBooking(Booking booking) throws SQLException, Exception {

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                String mysqlDateString = formatter.format(date);
                java.sql.Date sqlDate = java.sql.Date.valueOf( mysqlDateString );
                
           

		preparedStatement = connect
				.prepareStatement("insert into abcs.flight_bookings values (?, ?, ?, ?, ?, ?, ?)");

		preparedStatement.setInt(1, booking.getBookingID());
		preparedStatement.setInt(2, booking.getCustomer().getUserID());
		preparedStatement.setInt(3, booking.getFlight().getFlight().getFlightID());
		preparedStatement.setString(4, booking.getClassType());
		preparedStatement.setInt(5, booking.getBaggage());
		preparedStatement.setFloat(6, booking.getPrice());
                preparedStatement.setDate(7,sqlDate );
		preparedStatement.executeUpdate();

		db.close();
	}
        
        public ArrayList<Booking> getBookingList(Customer cust) throws Exception {

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                int bookingID;
                int flightNumber;
                String classType;
                int baggage;
                float price;
		FlightDBController db1 = new FlightDBController();
                Calendar cal = Calendar.getInstance();
                Date date=cal.getTime();
                
                Airline flight;
		ArrayList<Booking> bookList = new ArrayList<Booking>();
                

		preparedStatement = connect.prepareStatement("SELECT * FROM abcs.flight_bookings WHERE user_id = ?;");
                preparedStatement.setInt(1, cust.getUserID());
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			bookingID = resultSet.getInt("booking_id");
			flightNumber = resultSet.getInt("flight_number");
			classType = resultSet.getString("class");
			baggage = resultSet.getInt("baggage");
			price = resultSet.getFloat("booking_price");
                        flight = db1.createFlight(flightNumber);
                        Booking b =  new Booking.BBuilder(bookingID, cust, flight, baggage, classType, date).build();
                        //userBook = new Booking(bookingID, cust, flight, baggage, classType, price, date);
                        bookList.add(b);
                       
		}

		db.close();

		

		return bookList;
	}
        
        public void cancelBooking(int flightID, Booking book) throws Exception
        {
            DatabaseManager db;
            db = DatabaseManager.getInstance();
            FlightDBController db1 = new FlightDBController();
            db.connectDB();
            this.connect = db.returnConnection();
            
            //Delete from flight booking + -- capacity for whatever class
            Airline flight = db1.createFlight(flightID);
            
            flight.getFlight().getFlightID();
            //Booking 
            preparedStatement = connect.prepareStatement("DELETE FROM abcs.flight_bookings WHERE booking_id = ?");
            preparedStatement.setInt(1, book.getBookingID());
            preparedStatement.executeUpdate();
            
            String classType = book.getClassType();
            int airplaneID = flight.getAirplaneID();
            int capacity;
            
            switch(classType){
                case "First Class": capacity = flight.getFlight().getCurrentFirstCapacity() - 1;
                                    preparedStatement = connect.prepareStatement("UPDATE abcs.airplane SET current_first = ? WHERE airplane_id = ?");
                                    preparedStatement.setInt(1, capacity);
                                    break;
                case "Economy Class": capacity = flight.getFlight().getCurrentEconomicCapacity() - 1;
                                    preparedStatement = connect.prepareStatement("UPDATE abcs.airplane SET current_economy = ?  WHERE airplane_id = ?");
                                    preparedStatement.setInt(1, capacity);
                                    break;
                case "Business Class": capacity = flight.getFlight().getCurrentBusinessCapacity() - 1;
                                    preparedStatement = connect.prepareStatement("UPDATE abcs.airplane SET current_business = ?  WHERE airplane_id = ?");
                                    preparedStatement.setInt(1, capacity);
                                    break;
            
            
            }
            
          
            
            preparedStatement.setInt(2, airplaneID);
            preparedStatement.executeUpdate();

            db.close();
        }
        
        public Booking createBooking(int bookingID, Customer cust) throws Exception
        {
            
                DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                //Booking book = new Booking();
                int flightNumber;
                String classType;
                int baggage;
                float price;
		FlightDBController db1 = new FlightDBController();
                Date date;
                Booking b = new Booking();
                Airline flight;

		preparedStatement = connect.prepareStatement("SELECT * FROM abcs.flight_bookings WHERE booking_id = ?;");
                preparedStatement.setInt(1, bookingID);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			bookingID = resultSet.getInt("booking_id");
			flightNumber = resultSet.getInt("flight_number");
			classType = resultSet.getString("class");
			baggage = resultSet.getInt("baggage");
			price = resultSet.getFloat("booking_price");
                        date = resultSet.getDate("booking_date");
                        flight = db1.createFlight(flightNumber);
                        b =  new Booking.BBuilder(bookingID, cust, flight, baggage, classType, date).build();
                        //book = new Booking(bookingID, cust, flight, baggage, classType, price, date);
                  
                       
		}

		db.close();
                
                return b;
        }

}
