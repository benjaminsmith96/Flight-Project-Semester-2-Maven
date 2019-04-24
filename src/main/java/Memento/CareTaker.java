/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import java.util.List;
import java.util.ArrayList;
import Memento.*;

/**
 *
 * @author Cailean
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();
    
    public void add(Memento state){
        mementoList.add(state);
    }
    
    public Memento get(int index){
        return mementoList.get(index);
    }
    
    public int getSize(){
        return (mementoList.size() - 1);
    }
    
}
