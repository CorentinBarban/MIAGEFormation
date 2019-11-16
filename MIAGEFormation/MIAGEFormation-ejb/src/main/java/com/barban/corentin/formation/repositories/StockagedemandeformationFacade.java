/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.repositories;

import com.barban.corentin.formation.entities.Stockagedemandeformation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class StockagedemandeformationFacade extends AbstractFacade<Stockagedemandeformation> implements StockagedemandeformationFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGEFormation-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockagedemandeformationFacade() {
        super(Stockagedemandeformation.class);
    }
    
}
