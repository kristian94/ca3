/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



/**
 *
 * @author Kristian Nielsen
 */


@WebListener
public class BackgroundJobManager implements ServletContextListener {
    
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInit");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Update(), 0, 4, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
    }
}
