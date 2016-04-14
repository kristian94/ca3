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

public class XmlReader extends DefaultHandler {
    
    Date date;
    CurrencyFacade cf = new CurrencyFacade();
    RateFacade drf = new RateFacade();
    
    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        
        
        
        if(localName.equals("dailyrates")){
            date = Date.valueOf(attributes.getValue(0));
            return;
        }
        if(localName.equals("currency")) {
       
            Currency currency = new Currency();
            Rate rate = new Rate();

            currency.setCode(attributes.getValue(0));
            currency.setDesc(attributes.getValue(1));
            
            System.out.println(currency.getCode());
            
            
            

            rate.setCurrency(currency);
            rate.setDate(date);
            try{
                rate.setRate(Double.parseDouble(attributes.getValue(2)));
            }catch(NumberFormatException e){
                rate.setRate(-1);
            }
            
            
            currency.addRate(rate);
            
            cf.addCurrency(currency);
            
 
        }
    }


    public void getDailyRate() {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        
    }

}
