/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.business;

import DTO.SalleDTO;
import com.barban.corentin.patrimoine.entities.CalendrierSalle;
import com.barban.corentin.patrimoine.entities.Salle;
import com.barban.corentin.patrimoine.repositories.CalendrierSalleFacadeLocal;
import com.barban.corentin.patrimoine.repositories.SalleFacadeLocal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Corentin
 */
@Stateless
public class gestionPatrimoine implements gestionPatrimoineLocal {

    @EJB
    private CalendrierSalleFacadeLocal calendrierSalleFacade;

    @EJB
    private SalleFacadeLocal salleFacade;

    @Override
    public void editerStatutSalle(Integer idSalle, String statut, Date date) {
        Salle s = this.salleFacade.find(idSalle);
        Collection<CalendrierSalle> cs = s.getCalendrierSalleCollection();
        for (CalendrierSalle c : cs) {
            if (c.getCalendrier().getDatecalendrier().equals(date)) {
                c.setStatut(statut);
            }
        }
    }
}
