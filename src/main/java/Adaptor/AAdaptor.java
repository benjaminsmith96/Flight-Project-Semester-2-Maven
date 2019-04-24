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
public class AAdaptor implements AdaptorInterface {
   ConnectorAdaptor cAdapter; 
   Airline thisAirline;
   

   @Override
   public void planeType(String name, Airline newAirline) {		
      if(name.equalsIgnoreCase("Mid size Jet")){
            newAirline.setBaggage(30);
            System.out.println("Mid Size Jet Created");
      } 
      
      else if(name.equalsIgnoreCase("Light Jet") || name.equalsIgnoreCase("Heavy Jet")){
         cAdapter = new ConnectorAdaptor(name);
         cAdapter.planeType(name, newAirline);
      }
      
      else{
         System.out.println("Invalid PlaneType: " + name + " format not supported");
      }
   }   
}


