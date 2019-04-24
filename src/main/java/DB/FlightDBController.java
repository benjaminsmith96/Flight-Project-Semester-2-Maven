package DB;

import Adaptor.AAdaptor;
import Flights.JetsFP;
import Flights.AFactory;
import Flights.AlphaFlights;
import Flights.Airline;
import Flights.JetsFlights;
import Flights.BFactory;
import Flights.AlphaFP;
import Flights.FFactory;
import Flights.Flight;
import java.awt.Dimension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.sql.Time;

public class FlightDBController {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int update;

	public FlightDBController() {

	}

	public void viewFlights() throws Exception {

		String results = "";
		ArrayList<Airline> flightList = getFlightList();

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

		JTextArea textArea = new JTextArea(results);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, scrollPane, "Flights", JOptionPane.INFORMATION_MESSAGE);
	}

	public ArrayList<Airline> getFlightList() throws Exception {

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		int flightNumber = 0;
		ArrayList<Airline> flightList = new ArrayList<Airline>();
		ArrayList<Integer> flightNum = new ArrayList<Integer>();

		preparedStatement = connect.prepareStatement("SELECT flight_number FROM abcs.flight;");
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			flightNumber = resultSet.getInt("flight_number");
			flightNum.add(flightNumber);
                        
		}

		db.close();

		/*
		 * LAMBDA EXPRESSIONS
		 */
                
		flightNum.forEach(n -> {
			try {
				flightList.add(createFlight(n));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return flightList;
	}

	public Airline createFlight(int flightNumber) throws Exception {

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		int flightID = 0;
		Time arrivalTime = null;
		Date arrivalDate = null;
		String arrivalAirport = "";
		Time departureTime = null;
		Date departureDate = null;
		String departureAirport = "";
		int airplaneID = 0;
                int airlineNumber = 0;
                int currentFirst = 0;
		int currentBusiness = 0;
		int currentEconomic = 0;
                System.out.println(flightNumber);
		preparedStatement = connect.prepareStatement("SELECT * FROM abcs.flight WHERE flight_number = ?;");
		preparedStatement.setInt(1, flightNumber);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			flightID = resultSet.getInt("flight_number");
			arrivalTime = resultSet.getTime("arrival_time");
			arrivalDate = resultSet.getDate("arrival_date");
			arrivalAirport = resultSet.getString("arrival_airport");
			departureTime = resultSet.getTime("departure_time");
			departureDate = resultSet.getDate("departure_date");
			departureAirport = resultSet.getString("departure_airport");
                        airlineNumber = resultSet.getInt("airplane_id");
                        currentFirst = resultSet.getInt("currentFirst");
			currentBusiness = resultSet.getInt("currentBusiness");
			currentEconomic = resultSet.getInt("currentEconomy");
			
		}

		db.close();
                
                
            switch (airlineNumber) {
                case 1:
                {
                    FFactory flight = new AFactory();
                    AlphaFlights airline = (AlphaFlights) flight.createAirplane("Alpha");
                    AlphaFP flightPrice = (AlphaFP) flight.createFlightPrice("Alpha");
                    airline.setFlight(flightPrice);
                    airline.setAirplaneID(airlineNumber);
                    airline.getFlight().setArrivalAirport(arrivalAirport);
                    airline.getFlight().setDepartureAirport(departureAirport);
                    airline.getFlight().setArrivalTime(arrivalTime);
                    airline.getFlight().setDepartureTime(departureTime);
                    airline.getFlight().setDepartureDate(departureDate);
                    airline.getFlight().setArrivalDate(arrivalDate);
                    airline.getFlight().setFlightID(flightID);
                    airline.getFlight().setCurrentFirstCapacity(currentFirst);
                    airline.getFlight().setCurrentBusinessCapacity(currentBusiness);
                    airline.getFlight().setCurrentEconomicCapacity(currentEconomic);
                    
                    
                    return airline;
                    
                }
   
                case 2:
                {
                    FFactory flight = new BFactory();
                    JetsFlights airline = (JetsFlights) flight.createAirplane("Jets");
                    JetsFP flightPrice = (JetsFP) flight.createFlightPrice("Jets");
                    airline.setAirplaneID(airlineNumber);
                    airline.setFlight(flightPrice);
                    airline.getFlight().setArrivalAirport(arrivalAirport);
                    airline.getFlight().setDepartureAirport(departureAirport);
                    airline.getFlight().setArrivalTime(arrivalTime);
                    airline.getFlight().setDepartureTime(departureTime);
                    airline.getFlight().setDepartureDate(departureDate);
                    airline.getFlight().setArrivalDate(arrivalDate);
                    airline.getFlight().setFlightID(flightID);
                    airline.getFlight().setCurrentFirstCapacity(currentFirst);
                    airline.getFlight().setCurrentBusinessCapacity(currentBusiness);
                    airline.getFlight().setCurrentEconomicCapacity(currentEconomic);
                    
                    return airline;
                }
                
                case 3:
                {
                    AAdaptor planePicker = new AAdaptor();
                    Airline newAirline = new Airline() {};
                    Flight newFlight = new Flight() {};
                    newAirline.setFlight(newFlight);
                    
                    planePicker.planeType("Heavy Jet", newAirline);
                    newAirline.setAirplaneID(airlineNumber);
                    newAirline.getFlight().setFirstPrice(800);
                    newAirline.getFlight().setBusinessPrice(700);
                    newAirline.getFlight().setEconomicPrice(650);
                    newAirline.getFlight().setBaggagePrice(60);
                    newAirline.getFlight().setArrivalAirport(arrivalAirport);
                    newAirline.getFlight().setDepartureAirport(departureAirport);
                    newAirline.getFlight().setArrivalTime(arrivalTime);
                    newAirline.getFlight().setDepartureTime(departureTime);
                    newAirline.getFlight().setDepartureDate(departureDate);
                    newAirline.getFlight().setArrivalDate(arrivalDate);
                    newAirline.getFlight().setFlightID(flightID);
                    newAirline.getFlight().setCurrentFirstCapacity(currentFirst);
                    newAirline.getFlight().setCurrentBusinessCapacity(currentBusiness);
                    newAirline.getFlight().setCurrentEconomicCapacity(currentEconomic);
                    
                    return newAirline;
                }
                
                case 4:
                {
                    AAdaptor planePicker = new AAdaptor();
                    Airline newAirline = new Airline() {};
                    Flight newFlight = new Flight() {};
                    newAirline.setFlight(newFlight);
                    
                    planePicker.planeType("Mid size Jet", newAirline);
                    newAirline.setAirplaneID(airlineNumber);
                    newAirline.getFlight().setFirstPrice(500);
                    newAirline.getFlight().setBusinessPrice(400);
                    newAirline.getFlight().setEconomicPrice(350);
                    newAirline.getFlight().setBaggagePrice(30);
                    newAirline.getFlight().setArrivalAirport(arrivalAirport);
                    newAirline.getFlight().setDepartureAirport(departureAirport);
                    newAirline.getFlight().setArrivalTime(arrivalTime);
                    newAirline.getFlight().setDepartureTime(departureTime);
                    newAirline.getFlight().setDepartureDate(departureDate);
                    newAirline.getFlight().setArrivalDate(arrivalDate);
                    newAirline.getFlight().setFlightID(flightID);
                    newAirline.getFlight().setCurrentFirstCapacity(currentFirst);
                    newAirline.getFlight().setCurrentBusinessCapacity(currentBusiness);
                    newAirline.getFlight().setCurrentEconomicCapacity(currentEconomic);
                    
                    return newAirline;
                }
                
                case 5:
                {
                    AAdaptor planePicker = new AAdaptor();
                    Airline newAirline = new Airline() {};
                    Flight newFlight = new Flight() {};
                    newAirline.setFlight(newFlight);
                    
                    planePicker.planeType("Light Jet", newAirline);
                    newAirline.setAirplaneID(airlineNumber);
                    newAirline.getFlight().setFirstPrice(400);
                    newAirline.getFlight().setBusinessPrice(300);
                    newAirline.getFlight().setEconomicPrice(250);
                    newAirline.getFlight().setBaggagePrice(10);
                    newAirline.getFlight().setArrivalAirport(arrivalAirport);
                    newAirline.getFlight().setDepartureAirport(departureAirport);
                    newAirline.getFlight().setArrivalTime(arrivalTime);
                    newAirline.getFlight().setDepartureTime(departureTime);
                    newAirline.getFlight().setDepartureDate(departureDate);
                    newAirline.getFlight().setArrivalDate(arrivalDate);
                    newAirline.getFlight().setFlightID(flightID);
                    newAirline.getFlight().setCurrentFirstCapacity(currentFirst);
                    newAirline.getFlight().setCurrentBusinessCapacity(currentBusiness);
                    newAirline.getFlight().setCurrentEconomicCapacity(currentEconomic);
                    
                    return newAirline;
                }
                
                case 6:
                {
                    AAdaptor planePicker = new AAdaptor();
                    Airline newAirline = new Airline() {};
                    Flight newFlight = new Flight() {};
                    newAirline.setFlight(newFlight);
                    
                    planePicker.planeType("Light Jet", newAirline);
                    newAirline.setAirplaneID(airlineNumber);
                    newAirline.getFlight().setFirstPrice(400);
                    newAirline.getFlight().setBusinessPrice(300);
                    newAirline.getFlight().setEconomicPrice(250);
                    newAirline.getFlight().setBaggagePrice(10);
                    newAirline.getFlight().setArrivalAirport(arrivalAirport);
                    newAirline.getFlight().setDepartureAirport(departureAirport);
                    newAirline.getFlight().setArrivalTime(arrivalTime);
                    newAirline.getFlight().setDepartureTime(departureTime);
                    newAirline.getFlight().setDepartureDate(departureDate);
                    newAirline.getFlight().setArrivalDate(arrivalDate);
                    newAirline.getFlight().setFlightID(flightID);
                    newAirline.getFlight().setCurrentFirstCapacity(currentFirst);
                    newAirline.getFlight().setCurrentBusinessCapacity(currentBusiness);
                    newAirline.getFlight().setCurrentEconomicCapacity(currentEconomic);
                    
                    return newAirline;
                }
                   
                default:
                    return null;
            }

	}

	public void decreaseSeats(Airline flight, String classType) throws Exception {

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		if(classType.equals("First Class")) {
			preparedStatement = connect
					.prepareStatement("UPDATE abcs.flight SET currentFirst = ? WHERE flight_number = ?;");
			preparedStatement.setInt(1, flight.getFlight().getCurrentFirstCapacity());
			preparedStatement.setInt(2, flight.getFlight().getFlightID());
			update = preparedStatement.executeUpdate();
		}

		else if(classType.equals("Business Class")) {
			preparedStatement = connect
					.prepareStatement("UPDATE abcs.flight SET currentBusiness = ? WHERE flight_number = ?;");
			preparedStatement.setInt(1, flight.getFlight().getCurrentBusinessCapacity());
			preparedStatement.setInt(2, flight.getFlight().getFlightID());
			update = preparedStatement.executeUpdate();
		}

		else if(classType.equals("Economic Class")) {
			preparedStatement = connect
					.prepareStatement("UPDATE abcs.flight SET currentEconomic = ? WHERE flight_number = ?;");
			preparedStatement.setInt(1, flight.getFlight().getCurrentEconomicCapacity());
			preparedStatement.setInt(2, flight.getFlight().getFlightID());
			update = preparedStatement.executeUpdate();
		}

		db.close();
	}

}
