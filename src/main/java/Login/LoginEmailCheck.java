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
public class LoginEmailCheck {
    
    private String email;
    
    public void setEmail(String email){this.email = email;}
    
    public String getEmail(){ return email;}
    
    public boolean accountActive(String emailToCheck){
        
        if(emailToCheck.equals(getEmail()) ){
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            if(!matcher.matches()){
                return false;
            }
            return true;
        }
        
        return false;
    }
    
}
