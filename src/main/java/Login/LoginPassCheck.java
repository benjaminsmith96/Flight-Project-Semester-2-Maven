/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author Cailean
 */
public class LoginPassCheck {
    
    private String password;
    
    public void setPassword(String password){this.password = password;}
    
    public String getPassword(){ return password;}
    
    public boolean passwordCheck(String passwordToCheck){
        if(passwordToCheck.equals(getPassword()) ){
            if(password.length() < 8){
            return false;
        }
            
        String upperCase = "(.*[A-Z].*)";
        if(!password.matches(upperCase)){
            return false;
        }
        
        String lowerCase = "(.*[a-z].*)";
        if(!password.matches(lowerCase)){
            return false;
        }
        
        String numbers = "(.*[0-9].*)";
        if(!password.matches(numbers)){
            return false;
        }
            return true;
        }else{
            return false;
        }
    }
    
}
