/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import DTO.CompteRenduDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.entities.Formationcompose;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface GestionFormationLocal {

    Formation ajouterFormation(Stockagedemandeformation demandeformationkey,int nbParticipants,String statut);

    Stockagedemandeformation stockerDemandeFormation(String codeFormation, String intitule, Integer codeClient, Integer nbPersonneTotale, String nomClient);

    String demanderEtatFormation(Integer idFormation);

    List<CompteRenduDTO> retournerCompteRendus();

    void ajouterFormateurFormation(Formation f, int idFormateur);

    void ajouterSalleFormation(Formation f, int idSalle);

    void ajouterDateFormation(Formation f, Date dateFormation);

    void ajouterNbPersonne(Formation f, Integer nbPersonne);

     HashMap<Formation, Integer> compterEffectifFormation(String codeFormation, int capaciteMax);

    Formationcompose ajouterFormationCompose(Formation formation, Stockagedemandeformation demandeFormation, int nbParticipants);

    void editerStatutFormation(Formation formation,String statut);

    Formation recupererInformationFormation(Formation f);

}
