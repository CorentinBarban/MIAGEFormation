/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.business;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;


/**
 *
 * @author Corentin
 */
@Startup
@Singleton
public class Test {

    @EJB    
    private gestionPatrimoineLocal gestionPatrimoine;
    
    public Test() {
        
    }
    
    @PostConstruct
    void init (){
        Date date = new Date();
        gestionPatrimoine.editerStatutSalle(1, "DISPONIBLE", date);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
