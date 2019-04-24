/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interceptor;

/**
 *
 * @author Stephen
 */
public class ConcreteInterceptor implements Interceptor {

    @Override
    public void ConsumeService(Context c) {
        c.Service();
        c.putInTable();
    }
}
