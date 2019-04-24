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
public class ConnectorAdaptor implements AdaptorInterface {

   AdvancedAdaptorInterface advancedInterface;

   public ConnectorAdaptor(String name){
      if(name.equalsIgnoreCase("Light Jet") ){
         advancedInterface = new CreateLightJet();			
         
      }else if (name.equalsIgnoreCase("Heavy Jet")){
         advancedInterface = new CreateHeavyJet();
      }	
   }

   @Override
   public void planeType(String name, Airline newAirline) {
      if(name.equalsIgnoreCase("Light Jet")){
         advancedInterface.planeLightJet(name, newAirline);
      }
      else if(name.equalsIgnoreCase("Heavy Jet")){
         advancedInterface.planeHeavyJet(name, newAirline);
      }
   }
}