/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import entity.Rate;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;

/**
 *
 * @author Kristian Nielsen
 */
public class RateFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public void addRate(Rate rate) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(rate);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
    
    public List<Rate> getLatestRates(){
        EntityManager em = emf.createEntityManager();
        Date date = ((Rate) em.createNamedQuery("Rate.FindNewestDate").getResultList().get(0)).getDate();
        return em.createNamedQuery("Rate.FindByDate").setParameter("date", date).getResultList();
    }
    
    public List<Currency> getAllRates(){
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Currency.FindAll").getResultList();
    }
    
    public List<Rate> getRatesByCountryCode(String code){
        return null;
    }
    
    public Rate getNewestRateByCountryCode(String code){
        EntityManager em = emf.createEntityManager();
        List<Rate> rates = ((Currency)em.createNamedQuery("Currency.FindByCode").setParameter("code", code).getSingleResult()).getRates();
        return rates.get(0);
        
    }
}
