/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.business;

import DTO.FormateurDTO;
import Exceptions.FormateurNotFoundException;
import com.barban.corentin.RH.entities.CalendrierFormateur;
import com.barban.corentin.RH.entities.Formateur;
import com.barban.corentin.RH.repositories.FormateurFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Editer le statut d'un formateur pour une date donnée
     *
     * @param idFormateur
     * @param statut
     * @param date
     * @throws FormateurNotFoundException
     */
    @Override
    public void modifierStatutFormateur(Integer idFormateur, String statut, Date date) throws FormateurNotFoundException {

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

    /**
     * Lister toutes les dates pour lesquelles les formateurs sont disponibles
     *
     * @param listFormateurDemandees
     * @return
     */
    @Override
    public HashMap<FormateurDTO, List<Date>> fournirPlanningFormateur(List<FormateurDTO> listFormateurDemandees) {
        HashMap<FormateurDTO, List<Date>> listeFormateursDiponibles = new HashMap();
        for (FormateurDTO formateurDTO : listFormateurDemandees) {
            Formateur f = this.formateurFacade.find(formateurDTO.getIdFormateur());
            Collection<CalendrierFormateur> cs = f.getCalendrierFormateurCollection();
            List<Date> listeDateDispo = new ArrayList();
            for (CalendrierFormateur c : cs) {
                if (c.getStatut().equals("DISPONIBLE")) {
                    listeDateDispo.add(c.getCalendrier().getDatecalendrier());
                }
            }
            listeFormateursDiponibles.put(formateurDTO, listeDateDispo);
        }
        return listeFormateursDiponibles;
    }

    /**
     * Renvoie si le formateur existe
     *
     * @param IdFormateur
     * @return
     */
    @Override
    public boolean verifierExistenceFormateur(Integer IdFormateur) {
        Formateur f = this.formateurFacade.find(IdFormateur);

        if (f.equals(null)) {
            return false;
        } else {
            return true;
        }
    }

}
