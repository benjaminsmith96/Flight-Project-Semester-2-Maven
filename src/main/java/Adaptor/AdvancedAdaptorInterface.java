/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaptor;

import Flights.Airline;

/**
 *
 * @author Stephen
 */
public interface AdvancedAdaptorInterface {
    public void planeLightJet(String name, Airline newAirline);
    public void planeHeavyJet(String name, Airline newAirline);
}
