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
   public class CreateHeavyJet extends Airline implements AdvancedAdaptorInterface{

   @Override
   public void planeHeavyJet(String name, Airline newAirline) {
        newAirline.setBaggage(60);
        System.out.println("Heavy Jet Created");
   }

    @Override
    public void planeLightJet(String name, Airline newAirline) {
       
    }
}
