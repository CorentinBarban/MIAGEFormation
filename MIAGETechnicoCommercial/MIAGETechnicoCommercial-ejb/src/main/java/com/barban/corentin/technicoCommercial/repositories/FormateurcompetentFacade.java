/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.repositories;

import com.barban.corentin.technicoCommercial.entities.Formateurcompetent;
import com.barban.corentin.technicoCommercial.entities.Salleadequate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class FormateurcompetentFacade extends AbstractFacade<Formateurcompetent> implements FormateurcompetentFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGETechnicoCommercial-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormateurcompetentFacade() {
        super(Formateurcompetent.class);
    }
    
    @Override
    public Formateurcompetent findByKey(Integer formateurKey) {
        Formateurcompetent result = null;
        List<Formateurcompetent> formateurs = em.createNamedQuery("Formateurcompetent.findByFormateurkey")
                .setParameter("formateurkey", formateurKey).getResultList();
        if (!formateurs.isEmpty()) {
            result = formateurs.get(0);
        }
        return result;
    }
}
