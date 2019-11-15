/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import DTO.CompteRenduDTO;
import DTO.FormationDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maths
 */
@Local
public interface gestionCommercialeLocal {
    
    List<FormationDTO> recupererCatalogueFormations(); 
    
    void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, int codeclient);
    
    CompteRenduDTO editerCompteRendus();
    
    CompteRenduDTO creerCompteRendu(FormationDTO formation);
    
    String demanderStatutFormation(int idFormation);
    
    boolean validerExistenceFormation(int code);

    
}