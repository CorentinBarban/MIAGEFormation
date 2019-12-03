/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import DTO.CompteRenduDTO;
import DTO.FormateurDTO;
import DTO.FormationDTO;
import DTO.SalleDTO;
import Exceptions.ListeFormationsVideException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maths
 */
@Local
public interface gestionCommercialeLocal {
    
    void recupererCatalogueFormations(); 
    
    void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, Integer codeclient);
    
    CompteRenduDTO editerCompteRendus() throws ListeFormationsVideException;
    
    String demanderEtatFormation(int idFormation);
    
    boolean validerExistenceFormation(String code);

    List<FormateurDTO> recupererListeFormateurCompetent(String Code);
    
    List<SalleDTO> recupererListeSallesAdequates(String Code);
    
    FormationDTO recupererInformationFormationCatalogue(String codeFormation);

    
}
