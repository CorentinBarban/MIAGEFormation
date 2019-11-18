/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import DTO.FormationDTO;
import DTO.SalleDTO;
import Exceptions.SalleNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Corentin
 */
@Startup
@Singleton
public class Test {

    @EJB
    private gestionCommercialeLocal gestCommerciale;

    public Test() {
    }

    @PostConstruct
    void init() {
    }

    void testRecupererCatalogueFormations(){
        
    }
    
    void testMemoriserDemandeFormation(){
        
    }
    
    void testEditerCompteRendus(){
        
    }
    
    void testDemanderStatutFormation(){
        
    }
    
    void testValiderExistenceFormation(){
        
    }
    
    void testCreerCompteRendu(){
        
    }
    
    void testDemanderEtatFormation(){
        
    }
}
