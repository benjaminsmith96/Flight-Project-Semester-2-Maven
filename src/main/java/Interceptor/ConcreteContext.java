/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interceptor;

import DB.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Stephen
 */
public class ConcreteContext implements Context{

    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Level level;
    private Exception ex;
    private java.util.Date time;
    private java.sql.Date newtime;
    private String msg;
    DatabaseManager db;

    public ConcreteContext(Level lvl, Exception e, String message)
        {
            this.level = lvl;
            this.ex = e;
            this.msg = message;
            this.time = new Date();
            this.newtime = new java.sql.Date(time.getTime());
        }
    
    @Override
    public Level getLevel() {
       return level;
    }
    
    public String getMessage()
    {
        return msg;
    }

    @Override
    public Exception getException() {
        return ex;
    }


    @Override
    public Date getTime() {
       return time;
    }

   
    @Override
    public void Service() {
       Logger l = Logger.getLogger(ConcreteContext.class.getName());
       String lnSkip = "\n";
       l.log(this.getLevel(),  lnSkip + (this.getMessage() + lnSkip + this.getException().getClass().getSimpleName() + lnSkip + this.getTime()));
    }
    
    @Override
    public void putInTable()
    {
        db = DatabaseManager.getInstance();
        try {
            db.connectDB();
        } catch (Exception ex) {
            msg = "Error Detected";
            Logger.getLogger(ConcreteContext.class.getName()).log(Level.SEVERE, msg, ex);
        }
        try {
            this.connect = db.returnConnection();
        } catch (Exception ex) {
            msg = "Error Detected";
            Logger.getLogger(ConcreteContext.class.getName()).log(Level.SEVERE, msg, ex);
        }
       
        try {
            preparedStatement = connect.prepareStatement("insert into abcs.loginlogger values (?, ?,?,?)");
            preparedStatement.setString(1, this.getLevel().getClass().getName());
            preparedStatement.setString(2, this.getMessage());
            preparedStatement.setString(3, this.getException().getClass().getSimpleName());
            preparedStatement.setDate(4, newtime);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            msg = "Error Detected";
            Logger.getLogger(ConcreteContext.class.getName()).log(Level.SEVERE, msg, ex);
        } 
    }
}