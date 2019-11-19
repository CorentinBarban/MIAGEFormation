/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.business;

import DTO.SalleDTO;
import Exceptions.SalleNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private gestionPatrimoineLocal gestionPatrimoine;

    public Test() {
    }

    @PostConstruct
    void init() {
//        testEditerStatutSalle();
//        testListerSalleDisponible();
//        sendMessage();
//        try {
//           
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    void testEditerStatutSalle() {
        Date date = new Date();
        try {
            gestionPatrimoine.editerStatutSalle(1, "PRESSENTIE", date);
        } catch (SalleNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    void testListerSalleDisponible() {
        Date date = new Date();
        List<SalleDTO> listeSalle = new ArrayList<>();
        SalleDTO s1 = new SalleDTO();
        s1.setNom("Diamant");
        s1.setIdsalle(1);
        listeSalle.add(s1);

        SalleDTO s2 = new SalleDTO();
        s1.setNom("Rubis");
        s2.setIdsalle(3);
        listeSalle.add(s2);

        SalleDTO s3 = new SalleDTO();
        s1.setNom("Perle");
        s3.setIdsalle(7);
        listeSalle.add(s3);

        List<SalleDTO> listeSalleDisponibles = gestionPatrimoine.listerSalleDisponible(listeSalle, date);
        System.out.println(listeSalleDisponibles.toString());
    }

    void sendMessage() {

        SalleDTO s1 = new SalleDTO();
        s1.setNom("Diamant");
        s1.setIdsalle(1);
    }
}
