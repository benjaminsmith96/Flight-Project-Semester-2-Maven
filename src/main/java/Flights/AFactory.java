/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flights;
import Flights.JetsFlights;

/**
 *
 * @author Cailean
 */
public class AFactory implements FFactory{
    
    
    @Override
    public AlphaFlights createAirplane(String name) {
        if(name == "Alpha"){
            AlphaFlights flight = new AlphaFlights();
            return flight;
            // Do something
        }
        return null;
    }

    @Override
    public AlphaFP createFlightPrice(String name) {
       if(name == "Alpha"){
            AlphaFP flightPrice = new AlphaFP();
            return flightPrice;
            // Do something
        }
        return null;
    }
}
