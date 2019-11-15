/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import DTO.CompteRenduDTO;
import DTO.FormationDTO;
import com.barban.corentin.commercial.entities.Demandedeformation;
import com.barban.corentin.commercial.repositories.DemandedeformationFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author maths
 */
@Stateless
public class gestionCommerciale implements gestionCommercialeLocal {

    @EJB
    private DemandedeformationFacadeLocal demandedeformationFacade;

    @Override
    public List<FormationDTO> recupererCatalogueFormations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, int codeclient) {
        Demandedeformation demandeFormation = new Demandedeformation(nomClient, dateDemande, codeFormation, intituleFormation, codeclient);
        Demandedeformation dfObjet = this.demandedeformationFacade.create(demandeFormation);
    }

    @Override
    public CompteRenduDTO editerCompteRendus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String demanderStatutFormation(int idFormation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validerExistenceFormation(int code) {
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public CompteRenduDTO creerCompteRendu(FormationDTO formation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
