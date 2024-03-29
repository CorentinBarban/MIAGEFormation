/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.repositories;

import com.barban.corentin.technicoCommercial.entities.Formationcatalogue;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class FormationcatalogueFacade extends AbstractFacade<Formationcatalogue> implements FormationcatalogueFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGETechnicoCommercial-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormationcatalogueFacade() {
        super(Formationcatalogue.class);
    }

    @Override
    public Formationcatalogue findByCode(String code) {
        Formationcatalogue result = null;
        List<Formationcatalogue> formationCatalogue = em.createNamedQuery("Formationcatalogue.findByCode")
                .setParameter("code", code).getResultList();
        if (!formationCatalogue.isEmpty()) {
            result = formationCatalogue.get(0);
        }
        return result;
    }

}
