/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.business;

import Exceptions.FormateurNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private gestionRHLocal gestionRH;

    public Test() {
    }

    @PostConstruct
    void init() {
        testEditStatutFormateur();
    }

    void testEditStatutFormateur() {
        Date date = new Date();
        try {
            gestionRH.editStatutFormateur(1, "INDISPONIBLE", date);
        } catch (FormateurNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
