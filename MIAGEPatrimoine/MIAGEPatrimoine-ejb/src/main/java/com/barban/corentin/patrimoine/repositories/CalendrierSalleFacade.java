/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.repositories;

import com.barban.corentin.patrimoine.entities.CalendrierSalle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class CalendrierSalleFacade extends AbstractFacade<CalendrierSalle> implements CalendrierSalleFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGEPatrimoine-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalendrierSalleFacade() {
        super(CalendrierSalle.class);
    }
    
}
