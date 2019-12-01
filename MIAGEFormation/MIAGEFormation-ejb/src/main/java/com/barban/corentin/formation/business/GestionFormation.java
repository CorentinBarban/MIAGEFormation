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
import java.util.ArrayList;
import java.util.Date;
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
    public Formation ajouterFormation(Stockagedemandeformation demandeformation,int nbParticipants) {
        Formation f = new Formation();
        f.setStatut("EN ATTENTE");
        Formation formationCree = this.formationFacade.create(f);
        Formationcompose fc = new Formationcompose();
        FormationcomposePK fcPK = new FormationcomposePK(formationCree.getIdformation(),demandeformation.getIddemandeformation());
        fc.setFormationcomposePK(fcPK);
        fc.setNbparticipants(nbParticipants);
        this.formationcomposeFacade.create(fc);
        return formationCree;
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
    public List<Formationcompose> listerFormationNonRemplie(String codeFormation, int capaciteMax) {
        List<Formationcompose> listeFormation = this.formationcomposeFacade.findAll();
        List<Formationcompose> listeFormationNonRemplie = new ArrayList<>();
        if (listeFormation != null) {
            for (Formationcompose formation : listeFormation) {
                System.out.println(formation.toString());
                if ((formation.getNbparticipants() < capaciteMax) && formation.getStockagedemandeformation().getCodeformationcatalogue().equals(codeFormation) && formation.getFormation().getDateformation().compareTo(new Date()) > 0) {
                    listeFormationNonRemplie.add(formation);
                }
            }
        }

        return listeFormationNonRemplie;
    }

}
