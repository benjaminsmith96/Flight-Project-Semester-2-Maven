package Managers;

/*import Booking.BookingPrice;
import Booking.Price_baggage;
import Booking.Booking;
import Booking.BookingDecorator;
import Booking.Prototype;
*/
import java.sql.SQLException;
import BookingBuilder.Booking;
import DB.BookingDBController;
import DB.FlightDBController;
import Flights.Airline;
import Flights.Flight;
import Interfaces.I_Booking;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import Users.Customer;

public class BookingManager implements I_Booking {

	@Override
	public Booking makeBooking(Customer customer, Airline flight, int baggage, String type, Date date) {
               
                float price = 0;
		int bookingID = (int)(Math.random()*100);
                
                Booking b =  new Booking.BBuilder(bookingID, customer, flight, baggage, type, date).build();
                System.out.println("Here");
                System.out.println(b.getBookingID() + " " + b.getBookingDate());
		return b;
	}

	@Override
	public float calculatePrice(Airline flight, String type, int baggage) {

            int quantity = baggage;
            int baggagePrice = (int) flight.getFlight().getBaggagePrice();
            float classPrice = 0;
            
            float totalBaggagePrice = 0;
            totalBaggagePrice =  totalBaggagePrice + (quantity*baggagePrice);
            System.out.println(totalBaggagePrice);
            
            if(type.matches("First Class")){
                classPrice = flight.getFlight().getFirstPrice();
            }else if(type.matches("Business Class")){
                classPrice = flight.getFlight().getBusinessPrice();
            }else if(type.matches("Economy Class")){
                classPrice = flight.getFlight().getEconomicPrice();
            }
            
            totalBaggagePrice = totalBaggagePrice + classPrice;
            System.out.println(totalBaggagePrice);
            
            return totalBaggagePrice;
            
                
                
	}

	@Override
	public void insertBooking(Booking booking) throws SQLException, Exception {
		BookingDBController db = new BookingDBController();
		db.insertBooking(booking);
	}

	@Override
	public void changePrice(Booking booking, float price) {
		booking.setPrice(price);
	}

    @Override
    public ArrayList<Booking> getUserBookings(Customer cust) {
        ArrayList<Booking> userList = new ArrayList<Booking>();
        BookingDBController db = new BookingDBController();
            try {
                userList = db.getBookingList(cust);
            } catch (Exception ex) {
                Logger.getLogger(BookingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return userList;
        
    }

    @Override
    public void cancelFlight(int flightID, Booking book) {
        BookingDBController db = new BookingDBController();
        try {
                
                db.cancelBooking(flightID, book);
            } catch (Exception ex) {
                Logger.getLogger(BookingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public Booking createBooking(int bookingID, Customer cust) {
        BookingDBController db = new BookingDBController();
        Booking book = new Booking();
        try {
                book = db.createBooking(bookingID, cust);
            } catch (Exception ex) {
                Logger.getLogger(BookingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return book;
    }

    @Override
    public ArrayList<Booking> getAllUserBookings() {
        BookingDBController db = new BookingDBController();
        ArrayList<Booking> list = new ArrayList<Booking>();
            try {
                list = db.getFullBookingList();
            } catch (Exception ex) {
                Logger.getLogger(BookingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
    }

}
