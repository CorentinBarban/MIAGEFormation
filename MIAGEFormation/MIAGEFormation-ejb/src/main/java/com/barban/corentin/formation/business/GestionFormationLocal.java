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
    
    /**
     * Ajouter une nouvelle formation
     * 
     * @param demandeformationkey
     * @param nbParticipants
     * @param statut
     * @return 
     */
    Formation ajouterFormation(Stockagedemandeformation demandeformationkey, int nbParticipants, String statut);
    
    /**
     * Stocker une demande de formation lorsque le commercial fait la demande
     * 
     * @param codeFormation
     * @param intitule
     * @param codeClient
     * @param nbPersonneTotale
     * @param nomClient
     * @return 
     */
    Stockagedemandeformation stockerDemandeFormation(String codeFormation, String intitule, Integer codeClient, Integer nbPersonneTotale, String nomClient);
    
    /**
     * Recuperer l'etat d'une formation
     * @param idFormation
     * @return 
     */
    String demanderEtatFormation(Integer idFormation);
    
    /**
     * Retourner les informations pour le compte rendu
     * @return 
     */
    List<CompteRenduDTO> retournerCompteRendus();
    
    /**
     * Ajouter un formateur à une formation
     * @param f
     * @param idFormateur 
     */
    void ajouterFormateurFormation(Formation f, int idFormateur);
    
    /**
     * Ajouter une salle à une formation
     * @param f
     * @param idSalle 
     */
    void ajouterSalleFormation(Formation f, int idSalle);
    
    /**
     * Ajouter une date à une formation
     * @param f
     * @param dateFormation 
     */
    void ajouterDateFormation(Formation f, Date dateFormation);
    
    /**
     * Compter le nombre personne presentes sur une formation
     * 
     * @param codeFormation
     * @param capaciteMax
     * @return 
     */
    HashMap<Formation, Integer> compterEffectifFormation(String codeFormation, int capaciteMax);
    
    /**
     * Liee une demande de formation avec une formation 
     * 
     * @param formation
     * @param demandeFormation
     * @param nbParticipants
     * @return 
     */
    Formationcompose ajouterFormationCompose(Formation formation, Stockagedemandeformation demandeFormation, int nbParticipants);
    
    /**
     * Editer le statut d'une formation
     * 
     * @param formation
     * @param statut 
     */
    void editerStatutFormation(Formation formation, String statut);
    
    /**
     * Recuperer les informations liées à une Formation
     * @param f
     * @return 
     */
    Formation recupererInformationFormation(Formation f);

}
