/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interceptor;

import java.util.Date;
import java.util.logging.Level;



/**
 *
 * @author Stephen
 */
public interface Context {
    String getMessage();
    Level getLevel();
    Exception getException();
    Date getTime();
    void Service();
    void putInTable();
}
