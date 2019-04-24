package Managers;

import Flights.Airline;
import java.util.ArrayList;

import Interfaces.I_Flight;
import DB.FlightDBController;

public class FlightManager implements I_Flight {

	private ArrayList<Airline> flightList;

	@Override
	public ArrayList<Airline> getFlightList() throws Exception {

		FlightDBController db = new FlightDBController();
		flightList = db.getFlightList();

		return flightList;
	}

	@Override
	public Airline createFlight(int flightNumber) throws Exception {

		FlightDBController db = new FlightDBController();
		Airline flight = db.createFlight(flightNumber);

		return flight;
	}

	@Override
	public boolean checkAvaliability(Airline flight, String typeClass) {
		if(typeClass.equals("First Class"))
			return (flight.getFlight().getCurrentFirstCapacity() == 0);

		else if(typeClass.equals("Business Class"))
			return (flight.getFlight().getCurrentBusinessCapacity() == 0);

		else if(typeClass.equals("Economic Class"))
			return (flight.getFlight().getCurrentEconomicCapacity() == 0);

		return false;
	}

	@Override
	public void decreaseSeats(Airline flight, String typeClass) throws Exception {

		FlightDBController flightManager = new FlightDBController();

		if(typeClass.equals("First Class")) {
			flight.getFlight().setCurrentFirstCapacity(flight.getFlight().getCurrentFirstCapacity() - 1);
                       
			try {
				flightManager.decreaseSeats(flight, typeClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if(typeClass.equals("Business Class")){
			flight.getFlight().setCurrentBusinessCapacity(flight.getFlight().getCurrentBusinessCapacity() - 1);
			try {
				flightManager.decreaseSeats(flight, typeClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if(typeClass.equals("Economic Class")) {
			flight.getFlight().setCurrentEconomicCapacity(flight.getFlight().getCurrentEconomicCapacity() - 1);
			try {
				flightManager.decreaseSeats(flight, typeClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
