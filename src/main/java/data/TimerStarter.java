/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author josephawwal
 */
public class TimerStarter extends TimerTask {

    @Override
    public void run() {
        
        while(true){
            
            Thread t1 = new Thread(new CurrencyNB());
            t1.start();
            
            try {
                t1.wait(86400000);
            }
            catch(InterruptedException ex){
                new Thread(new TimerStarter()).start();
                
            }
        }
    }
    
    
    
}
