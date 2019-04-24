/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import DB.UserDBController;
import UI.ManagerMenu;
import UI.UserMenu;

/**
 *
 * @author Cailean
 */
public class UFactory {
    
    public User createUser(int role, String email, String password) throws Exception{
        
       
        UserDBController u = new UserDBController();
        
        switch (role) {
            case 1:
                Customer customer = new Customer();
                u.createUser(customer, email, password);
                UserMenu userMenu = new UserMenu(customer);
                userMenu.setVisible(true);
                return customer;
            case 2:
                Manager staff = new Manager();
                u.createUser(staff, email, password);
                ManagerMenu menu = new ManagerMenu(staff);
                menu.setVisible(true);
                return staff;
            default:
                return null;
        }
        }
    }
    
