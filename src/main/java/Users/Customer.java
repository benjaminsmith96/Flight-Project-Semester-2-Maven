/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import DB.MembershipDBController;
import Interfaces.I_Membership;

/**
 *
 * @author Cailean
 */
public class Customer extends User implements I_Membership {
    
    private int points;
    
    public Customer(){
        setRole(0);
    }
    
     public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;

    }

    @Override
    public void addPoints(int points, int userID) throws Exception{
        this.points += points;
        System.out.println();
        MembershipDBController db = new MembershipDBController();
        db.addMembershipPoints(userID, this.points);
    }

    @Override
    public void subtractPoints(int points, int userID) throws Exception{
        
        MembershipDBController db = new MembershipDBController();
        db.substractMembershipPoints(userID, points);
    }

    @Override
    public void cancelFlightPoints(int bookingID, int userID) throws Exception {
        MembershipDBController db = new MembershipDBController();
        int points = db.cancelFlightPoints(bookingID);
        subtractPoints(points, userID);
    }
    
    
}
