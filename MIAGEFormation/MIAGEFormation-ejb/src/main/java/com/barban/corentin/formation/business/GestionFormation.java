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
    public Formationcompose demanderFormation(String nomClient, Integer nbPersonne, String codeFormation, String intitule,Integer codeClient) {
        
        Stockagedemandeformation sf = stockerDemande(codeFormation,intitule,codeClient);
        Formation f = creationFormation(nomClient,codeFormation);
        FormationcomposePK fcPK = new FormationcomposePK(sf.getIddemandeformation(),f.getIdformation());
        Formationcompose fc = new Formationcompose(fcPK);
        fc.setNbpersonne(nbPersonne);
        return this.formationcomposeFacade.create(fc); 
    }
    
    public Formation creationFormation(String nomClient, String codeFormation){
        Formation f = new Formation();
        f.setNomclient(nomClient);
        f.setCodeformationcatalogue(codeFormation);
        return this.formationFacade.create(f);
    }
    
    /**
     * Stocker une demande de formation lorsque le commercial fait la demande
     *
     * @param codeFormation
     * @param intitule
     * @param codeClient
     * @return
     */
    @Override
    public Stockagedemandeformation stockerDemande(String codeFormation, String intitule, Integer codeClient) {
        Date dateDemande = new Date();
        Stockagedemandeformation demande = new Stockagedemandeformation();
        demande.setCodeclient(codeClient);
        demande.setCodeformation(codeFormation);
        demande.setIntituleformation(codeFormation);
        demande.setDatedemandeformation(dateDemande);
        return this.stockagedemandeformationFacade.create(demande);
    }

    /**
     * Fournir l'etat d'une formation (en attente, en projet, planifiée)
     *
     * @param formationCompose
     * @return
     */
    @Override
    public String demanderEtatFormation(Integer formationCompose) {
        return this.formationcomposeFacade.find(formationCompose).getStatut();
    }

    @Override
    public List<CompteRenduDTO> retournerCompteRendus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ajouterFormateurFormation(Formationcompose fc, int idFormateur) {
        this.formationcomposeFacade.find(fc.getFormationcomposePK()).setKeyformateur(idFormateur);
    }

    @Override
    public void ajouterSalleFormation(Formationcompose fc, int idSalle) {
        this.formationcomposeFacade.find(fc.getFormationcomposePK()).setKeysalle(idSalle);
    }

    @Override
    public void ajouterDateFormation(Formationcompose fc, Date dateFormation) {
        this.formationcomposeFacade.find(fc.getFormationcomposePK()).setDateformation(dateFormation);
    }

    @Override
    public void ajouterNbPersonne(Formationcompose fc, Integer nbPersonne) {
        this.formationcomposeFacade.find(fc.getFormationcomposePK()).setNbpersonne(nbPersonne);
    }

}
