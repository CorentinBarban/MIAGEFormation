/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import DTO.CompteRenduDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import com.barban.corentin.formation.repositories.FormationFacadeLocal;
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
    private StockagedemandeformationFacadeLocal stockagedemandeformationFacade;

    @EJB
    private FormationFacadeLocal formationFacade;
    
    
    @Override
    public Formation demanderFormation(String nomClient,Integer nbPersonne,String codeFormation,Stockagedemandeformation keyStockageDemandeFormation) {
        Formation f = new Formation();
        f.setNomclient(nomClient);
        f.setNbpersonne(nbPersonne);
        f.setCodeformationcatalogue(codeFormation);
        f.setStatut("EN ATTENTE");
        f.setKeystockagedemandeformation(keyStockageDemandeFormation);
        return this.formationFacade.create(f);
    }
    
    /**
     * Stocker une demande de formation lorsque le commercial fait la demande
     * @param codeFormation
     * @param intitule
     * @param codeClient 
     * @return  
     */
    @Override
    public Stockagedemandeformation stockerDemande(String codeFormation,String intitule,Integer codeClient) {
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
     * @param idFormation
     * @return 
     */
    @Override
    public String demanderEtatFormation(Integer idFormation) {
        Formation f = this.formationFacade.find(idFormation);
        return f.getStatut();
    }

    @Override
    public List<CompteRenduDTO> retournerCompteRendus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ajouterFormateurFormation(int idFormation, int idFormateur) {
        Formation f = this.formationFacade.find(idFormation);
        f.setKeyformateur(idFormateur);
    }

    @Override
    public void ajouterSalleFormation(int idFormation,int idSalle) {
        Formation f = this.formationFacade.find(idFormation);
        f.setKeysalle(idSalle);
    }

}
