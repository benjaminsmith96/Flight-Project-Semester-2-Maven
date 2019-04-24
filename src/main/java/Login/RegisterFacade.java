/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import DB.RegisterDBController;
import Interceptor.ConcreteContext;
import Interceptor.ConcreteInterceptor;
import Interceptor.Dispatcher;
import Interceptor.Interceptor;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Cailean
 */
public class RegisterFacade {
    
    private String fName;
    private String lName;
    private String contactNumber;
    private String email;
    private String password;
    private String gender;
    private String dob;
    private String message;
    
    LoginEmailCheck emailChecker;
    LoginPassCheck passChecker;
    RegNameCheck fNameChecker;
    RegNameCheck lNameChecker;
    RegContactCheck conChecker;
    
    public RegisterFacade(String fName, String lName, String email, String pass, String contactNumber, String dob, String gender){
        
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = pass;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.dob = dob;
        
        emailChecker = new LoginEmailCheck();
        passChecker = new LoginPassCheck();
        fNameChecker = new RegNameCheck();
        lNameChecker = new RegNameCheck();
        conChecker = new RegContactCheck();
        
        emailChecker.setEmail(email);
        passChecker.setPassword(password);
        fNameChecker.setName(fName);
        lNameChecker.setName(lName);
        conChecker.setNumber(contactNumber);
    }
    
    public String getFName(){return fName;}
    public String getLName(){return lName;}
    public String getEmail(){return email;}
    public String getNumber(){return contactNumber;}
    public String getPass(){return password;}
    public String getDOB(){return dob;}
    public String getGender(){return gender;}
    
    public void register() throws Exception{
        if(emailChecker.accountActive(getEmail()) && passChecker.passwordCheck(getPass())
                && fNameChecker.nameCheck(getFName()) && lNameChecker.nameCheck(getLName()) && conChecker.contactCheck(getNumber())){
            
           try{
            RegisterDBController db = new RegisterDBController();
            db.insertNewUser(getFName(), getLName(), getEmail(), getPass(), getNumber(), getDOB(), getGender());
            JOptionPane optionPane = new JOptionPane("Registered succesfully", JOptionPane.OK_OPTION);
            JDialog dialog = optionPane.createDialog("Success");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
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
            JOptionPane optionPane = new JOptionPane("Did not register succesfully, invalid values entered", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Failure");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
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
}
