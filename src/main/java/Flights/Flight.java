package Flights;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Cailean
 */
public abstract class Flight {

    protected int flightID;
    protected Time departureTime;
    protected Date departureDate;
    protected String departureAirport;
    protected Time arrivalTime;
    protected Date arrivalDate;
    protected String arrivalAirport;
    private float firstPrice;
    private float businessPrice;
    private float economicPrice;
    private float baggagePrice;
    protected int currentFirstCapacity;
    protected int currentBusinessCapacity;
    protected int currentEconomicCapacity;
    
 
    public int getFlightID() {return flightID;}
    public void setFlightID(int flightID) {this.flightID = flightID;}
    public Time getDepartureTime() {return departureTime;}
    public void setDepartureTime(Time departureTime) {this.departureTime = departureTime;}
    public Date getDepartureDate() {return departureDate;}
    public void setDepartureDate(Date departureDate) {this.departureDate = departureDate;}
    public String getDepartureAirport() {return departureAirport;}
    public void setDepartureAirport(String departureAirport) {this.departureAirport = departureAirport;}
    public Time getArrivalTime() {return arrivalTime;}
    public void setArrivalTime(Time arrivalTime) {this.arrivalTime = arrivalTime;}
    public Date getArrivalDate() {	return arrivalDate;}
    public void setArrivalDate(Date arrivalDate) {this.arrivalDate = arrivalDate;}
    public String getArrivalAirport() {return arrivalAirport;}
    public void setArrivalAirport(String arrivalAirport) {this.arrivalAirport = arrivalAirport;}
    public float getFirstPrice() {return firstPrice;}
    public void setFirstPrice(float firstPrice) {this.firstPrice = firstPrice;}
    public float getBusinessPrice() {return businessPrice;}
    public void setBusinessPrice(float businessPrice) {this.businessPrice = businessPrice;}
    public float getEconomicPrice() {return economicPrice;}
    public void setEconomicPrice(float economicPrice) {this.economicPrice = economicPrice;}
    public float getBaggagePrice() {return baggagePrice;}
    public void setBaggagePrice(float baggagePrice) {this.baggagePrice = baggagePrice;}
    public int getCurrentFirstCapacity() {return currentFirstCapacity;}
    public void setCurrentFirstCapacity(int currentFirstCapacity) {this.currentFirstCapacity = currentFirstCapacity;}
    public int getCurrentBusinessCapacity() {return currentBusinessCapacity;}
    public void setCurrentBusinessCapacity(int currentBusinessCapacity) {this.currentBusinessCapacity = currentBusinessCapacity;}
    public int getCurrentEconomicCapacity() {return currentEconomicCapacity;}
    public void setCurrentEconomicCapacity(int currentEconomicCapacity) {this.currentEconomicCapacity = currentEconomicCapacity;}

}
