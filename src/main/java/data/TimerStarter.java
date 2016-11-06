/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author josephawwal
 */
@WebListener
public class TimerStarter implements ServletContextListener
{

    private ScheduledExecutorService scheduler;

 
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(new CurrencyNB(), 0, TimeUnit.DAYS);
        scheduler.scheduleAtFixedRate(new CurrencyNB(), timer(), 24*60, TimeUnit.MINUTES);
    }
    
    public int timer()
    {
        Calendar now = Calendar.getInstance();
        
        int curHour = now.get(Calendar.HOUR_OF_DAY);
        int curMin = now.get(Calendar.MINUTE);
        int waitMin = minCount(curMin);
        int waitHour = hourCount(curHour);
        if (curMin==0&&curHour==0)
        {
            waitHour=24;
        }
        
        return waitMin + waitHour*60;
    }
    public int hourCount(int curHour)
    {
        if (curHour==14)
        {
            return 0;
        }
        else if(curHour>14)
        {
            return 14+24-curHour;
        }
        else if(curHour<14)
        {
            return 14-curHour;
        }
        return 0;
    }
    public int minCount(int curMin)
    {
        if (curMin==30)
        {
            return 0;
        }
        else if(curMin>30)
        {
            return -30-60+curMin;
        }
        else if(curMin<30)
        {
            return 30-curMin;
        }
        return 0;
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        scheduler.shutdownNow();
    }

}
