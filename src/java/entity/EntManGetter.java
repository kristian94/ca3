/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;

/**
 *
 * @author Kristian Nielsen
 */
public class EntManGetter {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    
}
