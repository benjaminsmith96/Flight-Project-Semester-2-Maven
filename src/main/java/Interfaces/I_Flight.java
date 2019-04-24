package Interfaces;

import Flights.Airline;
import java.util.ArrayList;

import Flights.Flight;

public interface I_Flight {

	/**
	 * Returns the list of available flights
	 * @return ArrayList of Flights
	 * @throws Exception
	 */
	public ArrayList<Airline> getFlightList() throws Exception;

	/**
	 * Creates a flight getting the information from the database
	 * @param flightNumber
	 * @return Flight
	 * @throws Exception
	 */
	public Airline createFlight(int flightNumber) throws Exception;

	/**
	 * Checks the availability of seats of a selected class flight
	 * @param flight
	 * @param typeClass
	 * @return True if there is no seats available
	 */
	public boolean checkAvaliability(Airline flight, String typeClass);

	/**
	 * Decreases the current seats number of a selected class flight
	 * @param flight
	 * @param typeClass
	 */
	public void decreaseSeats(Airline flight, String typeClass) throws Exception;

}
