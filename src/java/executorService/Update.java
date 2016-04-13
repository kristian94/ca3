/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executorService;

import facades.DailyRateFacade;

/**
 *
 * @author Kristian Nielsen
 */
public class Update implements Runnable {
    private static final DailyRateFacade drf = new DailyRateFacade();
    private static final XmlReader xr = new XmlReader();
    
    @Override
    public void run() {
        drf.persistDailyRate(xr.getDailyRate());
        System.out.println("ran");
    }
    
}
