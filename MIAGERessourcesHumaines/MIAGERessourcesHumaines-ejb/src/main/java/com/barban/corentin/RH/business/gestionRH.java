/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.business;

import Exceptions.FormateurNotFoundException;
import com.barban.corentin.RH.entities.CalendrierFormateur;
import com.barban.corentin.RH.entities.Formateur;
import com.barban.corentin.RH.repositories.FormateurFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Julie
 */
@Stateless
public class gestionRH implements gestionRHLocal {

    @EJB
    private FormateurFacadeLocal formateurFacade;

    @Override
    public void editStatutFormateur(Integer idFormateur, String statut, Date date) throws FormateurNotFoundException {
    
        Formateur f = this.formateurFacade.find(idFormateur);
        if (f == null) {
            throw new FormateurNotFoundException();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collection<CalendrierFormateur> cf = f.getCalendrierFormateurCollection();
        for (CalendrierFormateur c : cf) {
            if (dateFormat.format(c.getCalendrier().getDatecalendrier()).compareTo(dateFormat.format(date)) == 0) {
                c.setStatut(statut);
            }
        }
    }
}
