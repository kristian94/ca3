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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.eclipse.persistence.jpa.config.Cascade;

/**
 *
 * @author Kristian Nielsen
 */
@NamedQueries({
    @NamedQuery(name="DailyRate.FindAll", query="Select dr from DailyRate dr"),
    @NamedQuery(name="DailyRate.FindById", query="Select dr  from DailyRate dr where dr.id = :id")
})


@Entity
public class DailyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date id;
    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    private List<Currency> currencies = new ArrayList();

    public void addCurrency(Currency c){
        currencies.add(c);
    }
    
    public List<Currency> getCurrencies(){
        return currencies;
    }
    
    public Date getId() {
        return id;
    }

    public void setId(Date id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DailyRate)) {
            return false;
        }
        DailyRate other = (DailyRate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for (Currency currency : currencies) {
            str += "[" + currency.getCode() + "," + currency.getDesc() + "," + currency.getRate() + "]";
        }
        return "entity.DailyRate[ id=" + id + ", currency=" + str + " ]";
    }
    
}
