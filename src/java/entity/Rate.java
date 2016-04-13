/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.eclipse.persistence.jpa.config.Cascade;

/**
 *
 * @author Kristian Nielsen
 */
@NamedQueries({
    @NamedQuery(name = "Rate.FindAll", query = "Select rate from Rate rate"),
    @NamedQuery(name = "Rate.FindById", query = "Select rate  from Rate rate where rate.id = :id"),
    @NamedQuery(name = "Rate.FindByDate", query = "Select rate.date from Rate rate where rate.date = :date"),
    @NamedQuery(name = "Rate.FindNewestDate", query = "Select rate from Rate rate ORDER BY rate.date DESC")
})

//@IdClass(RateCompositeKey.class)
@Entity
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Currency currency;
    private double rate;
    private Date date;
//    private String currencyCode;

//    public String getCurrencyCode() {
//        return currencyCode;
//    }
//
//    public void setCurrencyCode(String currencyCode) {
//        this.currencyCode = currencyCode;
//    }
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
