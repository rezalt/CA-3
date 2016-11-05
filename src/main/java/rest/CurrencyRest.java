/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.DailyRate;
import facades.CurrencyFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author josephawwal
 */
@RolesAllowed("User")
@Path("currency/dailyrates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CurrencyRest {
    
    
    @Context
    private UriInfo context;
    private CurrencyFacade cf = new CurrencyFacade();
    
    public CurrencyRest(){
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getDailyRates(){
        List<DailyRate> rates = cf.getDailyRates();
        Gson gson = new Gson();
        return new Gson().toJson(rates);
        
    }
    
    
}
