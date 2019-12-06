/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.business;

import DTO.FormateurDTO;
import Exceptions.FormateurNotFoundException;
import com.barban.corentin.RH.entities.Formateur;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julie
 */
@Local
public interface gestionRHLocal {

    void modifierStatutFormateur(Integer idFormateur, String statut, Date date) throws FormateurNotFoundException;

    HashMap<FormateurDTO, List<Date>> fournirPlanningFormateur(List<FormateurDTO> listFormateurDemandees);

    boolean verifierExistenceFormateur(Integer idFormateur);

    List<FormateurDTO> listerFormateurs();
}
