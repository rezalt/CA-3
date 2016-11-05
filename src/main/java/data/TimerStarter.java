/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author josephawwal
 */

public class TimerStarter implements ServletContextListener
{

    private ScheduledExecutorService scheduler;

    public TimerStarter()
    {
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new CurrencyNB(), 0, 3, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        scheduler.shutdownNow();
    }

}
