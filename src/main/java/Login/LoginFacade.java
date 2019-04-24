/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import DB.LoginDBController;
import Interceptor.ConcreteContext;
import Interceptor.ConcreteInterceptor;
import Interceptor.Dispatcher;
import Interceptor.Interceptor;
import Interfaces.I_User;
import Managers.UserManager;
import UI.LoginMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Cailean
 */
public class LoginFacade {
    
    private String email;
    private String password;

    LoginEmailCheck emailChecker;
    LoginPassCheck passChecker;
    String message;
    
    
    public LoginFacade(String newEmail, String newPass){
        email = newEmail;
        password = newPass;
        passChecker = new LoginPassCheck();
        emailChecker = new LoginEmailCheck();
        passChecker.setPassword(password);
        emailChecker.setEmail(email);
    }
    
    public String getEmail(){ return email;}
    public String getPass(){return password;}
    
    public boolean login() throws Exception{
        if(emailChecker.accountActive(getEmail()) && passChecker.passwordCheck(getPass())){
                LoginDBController db = new LoginDBController();
                I_User userMan = new UserManager();
                
                if(db.loginCheck(email, password)){
                    try{
                        //System.out.println(test[4]);
                        userMan.createUser(email, password);
                        return true;
                    }
                    
                    catch(Exception e)
                    {
                           message = "Error Detected";
                           Interceptor in = new ConcreteInterceptor();
                           Dispatcher.getInstance().register(in);
                           Dispatcher.getInstance().dispatch(new ConcreteContext(Level.SEVERE, e, message));
                    }
                    
                }else{
                    try{
                        JOptionPane optionPane = new JOptionPane("Email or Password is not Valid", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        return false;
                        }
                    
                        catch(Exception e)
                        {
                            message = "Error Detected";
                            Interceptor in = new ConcreteInterceptor();
                            Dispatcher.getInstance().register(in);
                            Dispatcher.getInstance().dispatch(new ConcreteContext(Level.SEVERE, e, message));
                        }
                    }
                }
        return false;
    }    
}
