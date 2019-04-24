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
public class Originator {
    private String state;
    private String classType;
    private int baggageQuantity;
    
    public void setState(String state){
        this.state = state;
    }
    
    public String getState(){
        return state;
    }
    
    public void setClassType(String classType){
        this.classType = classType;
    }
    
    public String getClassType(){
        return this.classType;
    }
    
    public void setBaggageQ(int baggageQ){
        this.baggageQuantity = baggageQ;
    }
    
    public int getBaggageQ(){
        return this.baggageQuantity;
    }
    
    public Memento saveStateToMemento(){
        return new Memento(state, classType, baggageQuantity);
    }
    
    public void getStateFromMemento(Memento memento){
        state = memento.getState();
        classType = memento.getClassType();
        baggageQuantity = memento.getBaggageQuantity();
    }
    
}
