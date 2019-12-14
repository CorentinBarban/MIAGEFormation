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
     * @param demandeformationkey key de la demande de formation
     * @param nbParticipants nombre de participants 
     * @param statut statut
     * @return nouvelle formation
     */
    Formation ajouterFormation(Stockagedemandeformation demandeformationkey, int nbParticipants, String statut);
    
    /**
     * Stocker une demande de formation lorsque le commercial fait la demande
     * 
     * @param codeFormation code de la formation 
     * @param intitule intitule de la formation
     * @param codeClient code du client
     * @param nbPersonneTotale nombre de personne totale
     * @param nomClient nom du client
     * @return la demande de formation 
     */
    Stockagedemandeformation stockerDemandeFormation(String codeFormation, String intitule, Integer codeClient, Integer nbPersonneTotale, String nomClient);
    
    /**
     * Recuperer l'etat d'une formation
     * @param idFormation id de la formation 
     * @return l'etat d'une formation
     */
    String demanderEtatFormation(Integer idFormation);
    
    /**
     * Retourner les informations pour le compte rendu
     * @return  liste des compte rendus
     */
    List<CompteRenduDTO> retournerCompteRendus();
    
    /**
     * Ajouter un formateur à une formation
     * @param f formation 
     * @param idFormateur  id formation 
     */
    void ajouterFormateurFormation(Formation f, int idFormateur);
    
    /**
     * Ajouter une salle à une formation
     * @param f formation 
     * @param idSalle id de la salle 
     */
    void ajouterSalleFormation(Formation f, int idSalle);
    
    /**
     * Ajouter une date à une formation
     * @param f formation 
     * @param dateFormation date de la formation 
     */
    void ajouterDateFormation(Formation f, Date dateFormation);
    
    /**
     * Compter le nombre personne presentes sur une formation
     * 
     * @param codeFormation code de la formation 
     * @param capaciteMax capacité maximum
     * @return le nombre personne presentes sur une formation 
     */
    HashMap<Formation, Integer> compterEffectifFormation(String codeFormation, int capaciteMax);
    
    /**
     * Liee une demande de formation avec une formation 
     * 
     * @param formation formation 
     * @param demandeFormation demande de formation 
     * @param nbParticipants nombre de participant 
     * @return demande de formation avec une formation 
     */
    Formationcompose ajouterFormationCompose(Formation formation, Stockagedemandeformation demandeFormation, int nbParticipants);
    
    /**
     * Editer le statut d'une formation
     * 
     * @param formation formation
     * @param statut statut
     */
    void editerStatutFormation(Formation formation, String statut);
    
   /**
    * Recuperer les informations liées à une Formation
    * @return les informations liées à une Formation
    */
    List<Formation> recupererInformationFormation();

}
