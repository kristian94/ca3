/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executorService;

import entity.Currency;
import entity.Rate;
import facades.CurrencyFacade;
import facades.RateFacade;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;

public class XmlReader extends DefaultHandler {

    private Date date = null;
    private CurrencyFacade cf = new CurrencyFacade();
    private RateFacade drf = new RateFacade();
    private NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

//        if(localName.equals("dailyrates")){
//            date = Date.valueOf(attributes.getValue(0));
//            return;
//        }
//        if(localName.equals("currency")) {
//       
//            Currency currency = new Currency();
//            Rate rate = new Rate();
//
//            currency.setCode(attributes.getValue(0));
//            currency.setDesc(attributes.getValue(1));
//            
//            System.out.println(currency.getCode());
//            
//            
//            
//
//            rate.setCurrency(currency);
//            rate.setDate(date);
//            try{
//                rate.setRate(Double.parseDouble(attributes.getValue(2)));
//            }catch(NumberFormatException e){
//                rate.setRate(-1);
//            }
//            
//            
//            currency.addRate(rate);
//            
//            cf.addCurrency(currency);

        

        

        if (attributes.getValue("time") != null) {
            date = Date.valueOf(attributes.getValue("time"));
        } else if (attributes.getValue("currency") != null) {
            Rate r = new Rate();
            Currency c = cf.getCurrency(attributes.getValue("currency"));
            
            if (c == null) {
                c = new Currency();
                c.setCode(attributes.getValue("currency"));
                c.setDesc(attributes.getValue("name"));
            }
            r.setCurrency(c);
            r.setDate(date);
            
//            r.setRate(Double.parseDouble(attributes.getValue("rate")));
            try{
                r.setRate(format.parse(attributes.getValue("rate")).doubleValue());
            } catch (ParseException ex) {
                r.setRate(-1);
            }
                
            c.addRate(r);
            
            
            
            cf.addCurrency(c);
            
        }

//            switch(attributes.getQName(i)){
//                case("time"):
//                    
//                    break;
//                case("currency"):
//                    
//                    
//            }
        if (localName.equals("currency")) {

            Currency currency = new Currency();
            Rate rate = new Rate();

            currency.setCode(attributes.getValue(0));
            currency.setDesc(attributes.getValue(1));

            System.out.println(currency.getCode());

            rate.setCurrency(currency);
            rate.setDate(date);
            try {
                rate.setRate(Double.parseDouble(attributes.getValue(2)));
            } catch (NumberFormatException e) {
                rate.setRate(-1);
            }

            currency.addRate(rate);

            cf.addCurrency(currency);

        }
    }

    public void getDailyRate() {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            System.out.println("hey");
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesHistoryXML?lang=da");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}
