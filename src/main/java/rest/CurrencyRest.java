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
@Path("currency")
public class CurrencyRest
{
    
    @GET
    @Path("/dailyrates")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getDailyRates()
    {
        CurrencyFacade cf = new CurrencyFacade();
        List<DailyRate> rates = cf.getDailyRates();
        return new Gson().toJson(rates);

    }
    
    @GET
    @Path("/calculator/{amount}/{fromcurrency}/{tocurrency}")
    @Consumes(MediaType.APPLICATION_XML)
    public String returnCalculatedAmount(){
        return null;
    }
    

}
