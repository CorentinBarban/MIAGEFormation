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
public class gestionFormation implements gestionFormationLocal {

    @EJB
    private StockagedemandeformationFacadeLocal stockagedemandeformationFacade;

    @EJB
    private FormationFacadeLocal formationFacade;
    
    
    @Override
    public void demanderFormation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Stocker une demande de formation lorsque le commercial fait la demande
     * @param codeFormation
     * @param intitule
     * @param codeClient 
     */
    @Override
    public void stockerDemande(String codeFormation,String intitule,Integer codeClient) {
        Date dateDemande = new Date();
        Stockagedemandeformation demande = new Stockagedemandeformation(codeFormation,intitule,codeClient,dateDemande);
        this.stockagedemandeformationFacade.create(demande);
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

}
