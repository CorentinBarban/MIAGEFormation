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

    @Override
    public Formation ajouterFormation(Stockagedemandeformation demandeformation, int nbParticipants,String statut) {
        Formation f = new Formation();
        f.setStatut(statut);
        Formation formationCree = this.formationFacade.create(f);
        ajouterFormationCompose(formationCree, demandeformation, nbParticipants);
        return formationCree;
    }
    
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

    @Override
    public List<CompteRenduDTO> retournerCompteRendus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ajouterFormateurFormation(Formation f, int idFormateur) {
        this.formationFacade.find(f.getIdformation()).setKeyformateur(idFormateur);
    }

    @Override
    public void ajouterSalleFormation(Formation f, int idSalle) {
        this.formationFacade.find(f.getIdformation()).setKeysalle(idSalle);
    }

    @Override
    public void ajouterDateFormation(Formation f, Date dateFormation) {
        this.formationFacade.find(f.getIdformation()).setDateformation(dateFormation);
    }

    @Override
    public void ajouterNbPersonne(Formation f, Integer nbPersonne) {
//        this.formationFacade.find(f.getIdformation()).setNbparticipants(nbPersonne);
    }

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

    @Override
    public void editerStatutFormation(Formation formation,String statut) {
        this.formationFacade.find(formation.getIdformation()).setStatut(statut);
    }

    @Override
    public Formation recupererInformationFormation(Formation f) {
        return this.formationFacade.find(f.getIdformation());
    }
    
    
    
}
