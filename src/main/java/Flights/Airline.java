/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flights;

/**
 *
 * @author Cailean
 */
abstract public class Airline {
    
    protected int airplaneID;
    protected int firstCapacity;
    protected int businessCapacity;
    protected int economicCapacity;
    protected int baggage;
    protected Flight flight;
    
    public int getAirplaneID() {return airplaneID;}

    public void setAirplaneID(int airplaneID) {this.airplaneID = airplaneID;}

    public int getFirstCapacity() {return firstCapacity;}

    public void setFirstCapacity(int firstCapacity) {this.firstCapacity = firstCapacity;}

    public int getBusinessCapacity() {return businessCapacity;}

    public void setBusinessCapacity(int businessCapacity) {this.businessCapacity = businessCapacity;}

    public int getEconomicCapacity() {return economicCapacity;}

    public void setEconomicCapacity(int economicCapacity) {this.economicCapacity = economicCapacity;}
    
    public int getBaggage() {return baggage;}

    public void setBaggage(int baggage) {this.baggage = baggage;}
    
    public Flight getFlight(){return flight;};
    
    public void setFlight(Flight flight){this.flight = flight;};
    
}