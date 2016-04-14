/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facades.RateFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Kristian
 */
@Path("rate")
public class Rate {

    @Context
    private UriInfo context;

    private RateFacade rf = new RateFacade();
    private Gson gson = new Gson();
    
    public Rate() {
    }

    /**
     * Retrieves representation of an instance of rest.Rate
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{code}")
    @Produces("application/json")
    public String getRatesByCountryCode(@PathParam("code") String code) {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces("application/json")
    public String getLatestsRates(){
        return "hey";
//        return gson.toJson(rf.getLatestRates());
    }
    
    
    @GET
    @Path("/{currency01}/{amount}/{currency02}")
    @Produces("application/json")
    public String convertAmount(@PathParam("currency01") String currency01, @PathParam("amount") String amount, @PathParam("currency02") String currency02){
        throw new UnsupportedOperationException();
    }
    
    
}
