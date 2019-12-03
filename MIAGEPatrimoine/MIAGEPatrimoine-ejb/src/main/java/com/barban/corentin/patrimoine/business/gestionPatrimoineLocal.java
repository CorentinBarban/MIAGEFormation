/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.business;

import DTO.SalleDTO;
import Exceptions.SalleNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface gestionPatrimoineLocal {

    /**
     * Editer le statut d'une salle
     * @param idSalle Identifiant d'une salle
     * @param statut Statut
     * @param date date pour la modification
     * @throws SalleNotFoundException 
     */
    void editerStatutSalle(Integer idSalle, String statut, Date date) throws SalleNotFoundException;

    /**
     * Lister les salle disponibles parmis celles demandées pour une date données
     * @param listSallesDemandees 
     * @return 
     */
    HashMap<SalleDTO,List<Date>>listerSalleDisponible(List<SalleDTO> listSallesDemandees);
    
    /**
     * Vérifier si une salle existe
     * @param salleKey Identifiant d'une salle
     * @return booléen de réponse
     */
    boolean validerExistenceSalle(Integer salleKey);
}
