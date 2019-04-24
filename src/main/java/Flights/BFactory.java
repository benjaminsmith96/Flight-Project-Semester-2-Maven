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
public class BFactory implements FFactory{
    

    @Override
    public JetsFlights createAirplane(String name) {
        if(name == "Jets"){
            JetsFlights flight = new JetsFlights();
            return flight;
            // Do something
        }
        return null;
    }

    @Override
    public JetsFP createFlightPrice(String name) {
        if(name == "Jets"){
            JetsFP flightPrice = new JetsFP();
            return flightPrice;
            // Do something
        }
        return null;
    }
}

    

