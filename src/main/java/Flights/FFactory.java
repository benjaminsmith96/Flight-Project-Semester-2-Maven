/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flights;

import static javafx.scene.input.KeyCode.T;


/**
 *
 * @author Cailean
 */
    public interface FFactory {
    
    Airline createAirplane(String name);
    Flight createFlightPrice(String name);
    
}
