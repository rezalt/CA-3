/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Currency;
import facades.CurrencyFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author josephawwal
 */
@Path("currency")
public class CurrencyRest
{
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("/dailyrates")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDailyRates()
    {
        CurrencyFacade cf = new CurrencyFacade();
        List<Currency> rates = cf.getDailyRates();
        return Response.status(Response.Status.OK).entity(gson.toJson(rates)).build();
    }
    
//    @GET
//    @Path("/calculator/{amount}/{fromcurrency}/{tocurrency}")
//    @Consumes(MediaType.APPLICATION_XML)
//    public String returnCalculatedAmount(){
//        return null;
//    }
    

}
