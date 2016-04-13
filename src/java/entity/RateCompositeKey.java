/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author Kristian
 */
@Embeddable
public class RateCompositeKey implements Serializable {
    
    private double rate;
    private Date date;
    
    
    
    public RateCompositeKey(){
        
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof RateCompositeKey)) return false;
        if(obj == null) return false;
        RateCompositeKey pk = (RateCompositeKey) obj;
        return (pk.date == date && pk.rate == rate);
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
