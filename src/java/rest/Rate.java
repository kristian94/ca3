/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import facades.RateFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


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
    @Produces("application/json")
    public String getRates(){
        JsonArray json = new JsonArray();
        for (entity.Currency currency : rf.getAllRates()) {
            JsonObject currencyJson = new JsonObject();
            currencyJson.addProperty("code", currency.getCode());
            currencyJson.addProperty("description", currency.getDesc());
            JsonArray ratesArray = new JsonArray();
            for (entity.Rate rate : currency.getRates()) {
                JsonObject rateObj = new JsonObject();
                rateObj.addProperty("date", rate.getDate().toString());
                rateObj.addProperty("rate", rate.getRate());
                ratesArray.add(rateObj);
            }
            currencyJson.add("rates", ratesArray);
            json.add(currencyJson);
        }
        
        return gson.toJson(json);
    }
    
    @GET
    @Path("/latest")
    @Produces("application/json")
    public String getLatestsRates(){
        JsonArray json = new JsonArray();
        for (entity.Rate latestRate : rf.getLatestRates()) {
            JsonObject jo = new JsonObject();
            jo.addProperty("date", latestRate.getDate().toString());
            jo.addProperty("rate", latestRate.getRate());
            JsonObject currency = new JsonObject();
            currency.addProperty("code", latestRate.getCurrency().getCode());
            currency.addProperty("description", latestRate.getCurrency().getDesc());
            jo.add("currency", currency);
            json.add(jo);
        }
        
        return gson.toJson(json);
    }
    
    @GET
    @Path("/{code}")
    @Produces("application/json")
    public String getRatesByCountryCode(@PathParam("code") String code) {
        return gson.toJson("yep");
    }
    
    
    @GET
    @Path("/{amount}/{fromcurrency}/{tocurrency}")
    @Produces("text/html")
    public String convertAmount(@PathParam("amount") String amount, @PathParam("fromcurrency") String fromCurrency,  @PathParam("tocurrency") String toCurrency){
        double fromRate = rf.getNewestRateByCountryCode(fromCurrency).getRate();
        double toRate = rf.getNewestRateByCountryCode(toCurrency).getRate(); 
        
        double result = Double.parseDouble(amount) * fromRate / toRate;
        
        return String.valueOf(result);
    }
    
    
    
}
