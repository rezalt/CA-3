/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.DailyRate;
import facades.CurrencyFacade;
import facades.CurrencyFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author josephawwal
 */
@RolesAllowed("User")
@Path("currency/dailyrates")
public class CurrencyRest {
    
    
    @Context
    private UriInfo context;
    private CurrencyFacade cf = new CurrencyFacade();
    private Gson gson = new Gson();
    
    public CurrencyRest(){
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDailyRates(){
        List<DailyRate> rates = cf.getDailyRates();
        return Response.ok(gson.toJson(rates)).build();
    }
    
    
}
