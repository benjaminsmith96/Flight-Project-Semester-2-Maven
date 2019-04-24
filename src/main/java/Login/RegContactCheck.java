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
public class RegContactCheck {
    
    private String number;
    
    public void setNumber(String number){this.number = number;}
    
    public String getNumber(){ return number;}
    
    public boolean contactCheck(String numberToCheck){
        if(numberToCheck.equals(getNumber())){
            Pattern pattern = Pattern.compile("^08[35679]\\d{7}$");

            Matcher matcher = pattern.matcher(number);

            if (!matcher.matches()) {
             return false;
            }
            return true;
        }
         return false;
        
    }
    
    
}
