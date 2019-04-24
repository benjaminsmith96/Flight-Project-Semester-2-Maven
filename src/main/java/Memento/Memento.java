/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

/**
 *
 * @author Cailean
 */
public class Memento {
    private String state;
    private String classType;
    private int baggageQuantity;
    
    public Memento(String state, String classType, int baggageQuantity){
        this.state = state;
        this.classType = classType;
        this.baggageQuantity = baggageQuantity;
    }
    
    public String getState(){
        return state;
    }
    
    public String getClassType(){
        return classType;
    }
    
    public int getBaggageQuantity(){
        return baggageQuantity;
    }
    
}
