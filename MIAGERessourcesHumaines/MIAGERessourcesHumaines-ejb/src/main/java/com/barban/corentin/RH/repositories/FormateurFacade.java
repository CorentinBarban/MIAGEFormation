/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.repositories;

import com.barban.corentin.RH.entities.Formateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class FormateurFacade extends AbstractFacade<Formateur> implements FormateurFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGERessourcesHumaines-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormateurFacade() {
        super(Formateur.class);
    }
    
}
