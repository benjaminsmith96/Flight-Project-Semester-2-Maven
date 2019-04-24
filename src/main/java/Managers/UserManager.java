package Managers;

import BookingBuilder.Booking;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DB.MembershipDBController;
import DB.UserDBController;
import Flights.Airline;
import Flights.Flight;
import Interfaces.I_Flight;
import Interfaces.I_User;
import java.util.logging.Level;
import java.util.logging.Logger;
import UI.ManagerMenu;
import UI.UserMenu;
import Users.Customer;
import Users.Manager;
import Users.UFactory;
import Users.User;

public class UserManager implements I_User {

	@Override
	public void createUser(String username, String password) throws SQLException, Exception {

		UserDBController u = new UserDBController();
		MembershipDBController m = new MembershipDBController();
		int roleID = u.getRole(username, password);
                User test;
                UFactory user = new UFactory();
                user.createUser(roleID, username, password);
                
                
                
                
                
                  
	}

	@Override
	public void viewFlights() throws Exception {

		I_Flight flightManager = new FlightManager();
		String results = "";
		ArrayList<Airline> flightList = flightManager.getFlightList();

		for(int i = 0; i < flightList.size(); i++) {
			results += "\nFlight Number: ";
			results += flightList.get(i).getFlight().getFlightID();
			results += "\n";
			results += "\nArrival Time: ";
			results += flightList.get(i).getFlight().getArrivalTime();
			results += "\n";
			results += "\nArrival Date: ";
			results += flightList.get(i).getFlight().getArrivalDate();
			results += "\n";
			results += "\nArrival Airport: ";
			results += flightList.get(i).getFlight().getArrivalAirport();
			results += "\n";
			results += "\nDeparture Time: ";
			results += flightList.get(i).getFlight().getDepartureTime();
			results += "\n";
			results += "\nDeparture Date: ";
			results += flightList.get(i).getFlight().getDepartureDate();
			results += "\n";
			results += "\nDeparture Airport: ";
			results += flightList.get(i).getFlight().getDepartureAirport();
			results += "\n";
			results += "\nAirplane ID: ";
			results += flightList.get(i).getAirplaneID();
			results += "\n\n--------------------------";
			results += "\n\n\n";
		};

		// Results added together to see the scrollpane
		JTextArea textArea = new JTextArea(results);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, scrollPane, "Flights", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public String getUserDetails(User user) {
		return user.toString();
	}

    @Override
    public ArrayList<Customer> getUserList() {
        ArrayList<Customer> userList = new ArrayList<Customer>();
        UserDBController db = new UserDBController();
            try {
                userList = db.getUserList();
            } catch (Exception ex) {
                Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return userList;
    }

    @Override
    public ArrayList<Booking> getSalesList() {
        ArrayList<Booking> salesList = new ArrayList<Booking>();
        
        return salesList;
    }

    @Override
    public void removeUser(int userID) {
        UserDBController db = new UserDBController();
            try {
                db.removeUser(userID);
            } catch (Exception ex) {
                Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
