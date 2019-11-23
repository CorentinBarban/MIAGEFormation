/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.repositories;

import com.barban.corentin.commercial.entities.Demandedeformation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class DemandedeformationFacade extends AbstractFacade<Demandedeformation> implements DemandedeformationFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGECommercial-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandedeformationFacade() {
        super(Demandedeformation.class);
    }
    
}
