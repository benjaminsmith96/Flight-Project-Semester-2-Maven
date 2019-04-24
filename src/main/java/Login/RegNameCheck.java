/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Cailean
 */
public class RegNameCheck {
    
    private String name;
    
    public void setName(String name){this.name = name;}
    
    public String getName(){ return name;}
    
    public boolean nameCheck(String nameToCheck){
        if(nameToCheck.equals(getName())){
            Pattern pattern = Pattern.compile("[a-zA-Z]*");

            Matcher matcher = pattern.matcher(name);

            if (!matcher.matches()) {
                return false;
            }

            return true;
      }else{
            return false;
        }
       
    }
    
}
