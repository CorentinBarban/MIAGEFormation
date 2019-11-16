/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import DTO.CompteRenduDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.repositories.FormationFacadeLocal;
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
    private FormationFacadeLocal formationFacade;
    
    
    @Override
    public void demanderFormation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void stockerDemande() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
