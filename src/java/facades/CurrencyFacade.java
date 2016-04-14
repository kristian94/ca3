/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;

/**
 *
 * @author Kristian
 */
public class CurrencyFacade {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    
    public void addCurrency(Currency curr){
        
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            
            em.merge(curr);
            
            em.getTransaction().commit();
            
        }finally{
            em.close();
        }
    }
    
    public Currency getCurrency(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Currency.class, id);
    }
    
    
    
    
}
