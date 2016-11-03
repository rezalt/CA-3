/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;
import data.CurrencyNB;
import entity.DailyRate;
import java.util.List;
/**
 *
 * @author josephawwal
 */
public class CurrencyFacade {
    
    public CurrencyFacade(){
        
    }
    
    public List<DailyRate> getDailyRates(){
        return CurrencyNB.getDailyRates();
    }
    
}
