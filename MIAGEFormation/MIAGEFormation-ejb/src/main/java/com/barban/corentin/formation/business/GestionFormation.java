/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import DTO.CompteRenduDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.entities.Formationcompose;
import com.barban.corentin.formation.entities.FormationcomposePK;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import com.barban.corentin.formation.repositories.FormationFacadeLocal;
import com.barban.corentin.formation.repositories.FormationcomposeFacadeLocal;
import com.barban.corentin.formation.repositories.StockagedemandeformationFacadeLocal;
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
public class GestionFormation implements GestionFormationLocal {

    @EJB
    private FormationcomposeFacadeLocal formationcomposeFacade;

    @EJB
    private StockagedemandeformationFacadeLocal stockagedemandeformationFacade;

    @EJB
    private FormationFacadeLocal formationFacade;
    
    /**
     * Ajouter une nouvelle formation
     * @param demandeformation
     * @param nbParticipants
     * @param statut
     * @return 
     */
    @Override
    public Formation ajouterFormation(Stockagedemandeformation demandeformation, int nbParticipants,String statut) {
        Formation f = new Formation();
        f.setStatut(statut);
        Formation formationCree = this.formationFacade.create(f);
        ajouterFormationCompose(formationCree, demandeformation, nbParticipants);
        return formationCree;
    }
    
    /**
     * Liee une demande de formation avec une formation 
     * @param formation
     * @param demandeFormation
     * @param nbParticipants
     * @return 
     */
    @Override
    public Formationcompose ajouterFormationCompose(Formation formation, Stockagedemandeformation demandeFormation, int nbParticipants) {
        Formationcompose fc = new Formationcompose();
        FormationcomposePK fcPK = new FormationcomposePK(formation.getIdformation(), demandeFormation.getIddemandeformation());
        fc.setFormationcomposePK(fcPK);
        fc.setNbparticipants(nbParticipants);
        return this.formationcomposeFacade.create(fc);
    }


    /**
     * Stocker une demande de formation lorsque le commercial fait la demande
     *
     * @param codeFormation
     * @param intitule
     * @param codeClient
     * @param nbPersonneTotale
     * @return
     */
    @Override
    public Stockagedemandeformation stockerDemandeFormation(String codeFormation, String intitule, Integer codeClient, Integer nbPersonneTotale, String nomClient) {
        Date dateDemande = new Date();
        Stockagedemandeformation demande = new Stockagedemandeformation();
        demande.setCodeclient(codeClient);
        demande.setCodeformationcatalogue(codeFormation);
        demande.setIntituleformation(codeFormation);
        demande.setDatedemandeformation(dateDemande);
        demande.setNbpersonne(nbPersonneTotale);
        demande.setNomclient(nomClient);
        return this.stockagedemandeformationFacade.create(demande);
    }

    /**
     * Fournir l'etat d'une formation (en attente, en projet, planifiée)
     *
     * @return
     */
    @Override
    public String demanderEtatFormation(Integer idFormation) {
        return this.formationFacade.find(idFormation).getStatut();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public List<CompteRenduDTO> retournerCompteRendus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Ajouter un formateur a une formation
     * @param f Object Formation
     * @param idFormateur Identifiant d'un formateur
     */
    @Override
    public void ajouterFormateurFormation(Formation f, int idFormateur) {
        this.formationFacade.find(f.getIdformation()).setKeyformateur(idFormateur);
    }
    
    /**
     * Ajouter une salle à une formation
     * @param f Object Formation
     * @param idSalle 
     */
    @Override
    public void ajouterSalleFormation(Formation f, int idSalle) {
        this.formationFacade.find(f.getIdformation()).setKeysalle(idSalle);
    }
    
    /**
     * Ajouter une date pour une formation
     * @param f Object Formation
     * @param dateFormation Date de formation
     */
    @Override
    public void ajouterDateFormation(Formation f, Date dateFormation) {
        this.formationFacade.find(f.getIdformation()).setDateformation(dateFormation);
    }
    
    /**
     * Compter le nombre personne presentes sur une formation
     * @param codeFormation Code de la formation
     * @param capaciteMax Capacite max de la formation
     * @return 
     */
    @Override
    public HashMap<Formation, Integer> compterEffectifFormation(String codeFormation, int capaciteMax) {
        List<Formationcompose> listeFormation = this.formationcomposeFacade.findAll();
        HashMap<Formation, Integer> listeFormationNonRemplie = new HashMap<>();
        if (listeFormation != null) {
            for (Formationcompose formationcompte : listeFormation) {
                if ((formationcompte.getNbparticipants() < capaciteMax) && formationcompte.getStockagedemandeformation().getCodeformationcatalogue().equals(codeFormation)) {
                    if (!listeFormationNonRemplie.containsKey(formationcompte.getFormation())) {
                        listeFormationNonRemplie.put(formationcompte.getFormation(), formationcompte.getNbparticipants());
                    } else {
                        listeFormationNonRemplie.put(formationcompte.getFormation(), listeFormationNonRemplie.get(formationcompte.getFormation()) + formationcompte.getNbparticipants());
                    }
                }
            }
           
        }
        return listeFormationNonRemplie;
    }
    
    /**
     * Editer le statut d'une formation
     * 
     * @param formation Object Formation
     * @param statut Statut de la formation
     */
    @Override
    public void editerStatutFormation(Formation formation,String statut) {
        this.formationFacade.find(formation.getIdformation()).setStatut(statut);
    }
    
    /**
     * Recuperer les informations liées à une Formation
     * @return 
     */
    @Override
    public List<Formation> recupererInformationFormation() {
        return this.formationFacade.findAll();
    }
    
    
    
}
