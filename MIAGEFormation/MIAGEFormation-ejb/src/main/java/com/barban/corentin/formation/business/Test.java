/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import javax.annotation.PostConstruct;
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
    private gestionFormationLocal gestionFormation;

    public Test() {
    }

    @PostConstruct
    void init() {
        demanderEtatFormation();
//        stockerDemandeFormation();
    }

    void stockerDemandeFormation() {
        this.gestionFormation.stockerDemande("F01", " Base de données", 1543);
    }

    void demanderEtatFormation() {
        System.out.println(this.gestionFormation.demanderEtatFormation(1));
    }
}
