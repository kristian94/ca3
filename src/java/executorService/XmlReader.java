/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executorService;

import entity.Currency;
import entity.DailyRate;
import facades.DailyRateFacade;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.sql.Date;

public class XmlReader extends DefaultHandler {
    
    private static DailyRate dr = new DailyRate();
    
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Currency c = new Currency();
        System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
            
            switch (attributes.getLocalName(i)) {
                case "code":
                    c.setCode(attributes.getValue(i));
                    break;
                case "desc":
                    c.setDesc(attributes.getValue(i));
                    break;
                case "rate":
                    double rate;
                    try {
                        rate = Double.parseDouble(attributes.getValue(i));
                    } catch (NumberFormatException e) {
                        rate = -1; //input string was most likely "-"
                    }
                    c.setRate(rate);
                    break;
                case "id":
                    Date date = Date.valueOf(attributes.getValue(i));
                    dr.setId(date);
                    break;
                default:
                    break;
            }
            System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
        }
        if (c.getCode() != null && c.getDesc() != null) {
            dr.addCurrency(c);
        }        
        System.out.println("");
    }

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public DailyRate getDailyRate() {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return dr;
    }

}
