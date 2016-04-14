/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

/**
 *
 * @author Kristian
 */
public class Tester {
    public static void main(String[] args) {
        
        
        
        RateFacade rf = new RateFacade();
        
        System.out.println(rf.getNewestRateByCountryCode("AUD").getRate());
        
    }
}
