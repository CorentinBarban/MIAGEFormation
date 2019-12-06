/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.businesses;

import DTO.FormationDTO;
import Exceptions.FormationCatalogueException;
import com.barban.corentin.technicoCommercial.entities.Formationcatalogue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author jdetr
 */
@Startup
@Singleton
public class Test {

    @EJB
    private gestionTechnicoCommercialeLocal gestionTC;

    public Test() {
    }

    @PostConstruct
    void init() {
//        testCreateFormationCatalogue();
    }

    void testCreateFormationCatalogue() {
        try {
            FormationDTO fc = gestionTC.ajouterFormationCatalogue("toto", "apprendre les blagues", "primaire", "court", 6, 12, 12.5);
            System.out.println(fc.toString());
        } catch (FormationCatalogueException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

