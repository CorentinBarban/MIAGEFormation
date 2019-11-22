/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.business;

import DTO.FormateurDTO;
import Exceptions.FormateurNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private gestionRHLocal gestionRH;

    public Test() {
    }

    @PostConstruct
    void init() {
        // testEditStatutFormateur();
        //testLstFormateurDispo();
        //testExistenceFormateur();
    }

    void testModifStatutFormateur() {
        Date date = new Date();
        try {
            gestionRH.modifierStatutFormateur(1, "TEST", date);
        } catch (FormateurNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void testLstFormateurDispo() {
        Date date = new Date();
        List<FormateurDTO> listeFormateur = new ArrayList<>();
        List<FormateurDTO> listeFormateurDisponibles;

        FormateurDTO f1 = new FormateurDTO();
        f1.setNom("Diamant");
        f1.setIdFormateur(1);
        listeFormateur.add(f1);

        FormateurDTO f2 = new FormateurDTO();
        f2.setNom("Emeraude");
        f2.setIdFormateur(2);
        listeFormateur.add(f2);

        FormateurDTO f3 = new FormateurDTO();
        f1.setNom("Rubis");
        f3.setIdFormateur(3);
        listeFormateur.add(f3);

        listeFormateurDisponibles = gestionRH.fournirPlanningFormateur(listeFormateur, date);
        System.out.println("Liste des formateurs --------------------------\n" + listeFormateurDisponibles.toString());
    }

    void testExistenceFormateur() {
        boolean result;
        result = gestionRH.verifierExistenceFormateur(1);
        System.out.println("Le formateur avec l'id: 1 est : " + result);
    }

}
