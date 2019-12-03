/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.business;

import DTO.SalleDTO;
import Exceptions.SalleNotFoundException;
import com.barban.corentin.patrimoine.entities.CalendrierSalle;
import com.barban.corentin.patrimoine.entities.Salle;
import com.barban.corentin.patrimoine.repositories.SalleFacadeLocal;
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
 * @author Corentin
 */
@Stateless
public class gestionPatrimoine implements gestionPatrimoineLocal {

    @EJB
    private SalleFacadeLocal salleFacade;

    /**
     * Editer le statut d'une salle pour une date donnée
     *
     * @param idSalle identifiant d'une salle
     * @param statut statut à modifier
     * @param date date de la modification
     * @throws Exceptions.SalleNotFoundException
     */
    @Override
    public void editerStatutSalle(Integer idSalle, String statut, Date date) throws SalleNotFoundException {

        Salle s = this.salleFacade.find(idSalle);
        if (s == null) {
            throw new SalleNotFoundException();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collection<CalendrierSalle> cs = s.getCalendrierSalleCollection();
        for (CalendrierSalle c : cs) {
            if (dateFormat.format(c.getCalendrier().getDatecalendrier()).compareTo(dateFormat.format(date)) == 0) {
                c.setStatut(statut);
            }
        }
    }

    /**
     * Lister toutes les dates pour lesquelles les salle sont disponibles
     *
     * @param listSallesDemandees Liste des salles pressenties
     * @return
     */
    @Override
    public HashMap<SalleDTO, List<Date>> listerSalleDisponible(List<SalleDTO> listSallesDemandees) {

        HashMap<SalleDTO, List<Date>> listeSallesDiponibles = new HashMap();
        for (SalleDTO salleDTO : listSallesDemandees) {
            Salle s = this.salleFacade.find(salleDTO.getIdsalle());
            Collection<CalendrierSalle> cs = s.getCalendrierSalleCollection();
            List<Date> listeDateDispo = new ArrayList();
            for (CalendrierSalle c : cs) {
                if (c.getStatut().equals("DISPONIBLE")) {
                    listeDateDispo.add(c.getCalendrier().getDatecalendrier());
                }
            }
            listeSallesDiponibles.put(salleDTO, listeDateDispo);
        }
        return listeSallesDiponibles;
    }

    /**
     * Verfier l'existance d'une salle
     * @param salleKey Identifiant d'une salle
     * @return
     */
    @Override
    public boolean validerExistenceSalle(Integer salleKey) {
        Salle s = this.salleFacade.find(salleKey);
        return s != null;
    }

}
