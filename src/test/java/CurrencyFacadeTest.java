/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.CurrencyNB;
import deploy.DeploymentConfiguration;
import facades.CurrencyFacade;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;

/**
 *
 * @author josephawwal
 */
public class CurrencyFacadeTest {
    
    private CurrencyFacade facade;
    Thread t;
    
    
    
    public CurrencyFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        DeploymentConfiguration.setTestModeOn();
        
    }
    
    
    
    @Before
    public void setUp() throws InterruptedException {
        
        facade = new CurrencyFacade();
        t = new Thread(new CurrencyNB());
        t.start();
        t.sleep(400);
        
    }
    
    
    @After
    public void tearDown() {
        t.interrupt();
    
    }
    
    
    @Test
    public void testGetDailyRates(){
        int size = facade.getDailyRates().size();
        assertEquals(33, size);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
