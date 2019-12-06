/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.services;

import DTO.FormateurDTO;
import DTO.FormationDTO;
import DTO.SalleDTO;
import Exceptions.FormateurNotFoundException;
import Exceptions.FormationCatalogueNotFoundException;
import Exceptions.LienFormateurFormationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jdetr
 */
@Local
public interface ServiceTechnicoCommercialLocal {
    
    FormationDTO consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException;
    
    List<FormationDTO> listerCatalogueFormations();

    List<SalleDTO> rechercherSallesAdequates(String code);

    List<FormateurDTO> rechercherFormateurAdequats(String code);
    
    boolean ajouterFormateurDansFormation(String code, int formateurkey) throws FormateurNotFoundException, LienFormateurFormationException;
    
    boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormateurNotFoundException, LienFormateurFormationException;
    
    boolean ajouterSalleDansFormation(String code, int sallekey) throws FormateurNotFoundException, LienFormateurFormationException;
    
    boolean supprimerSalleDeFormation(String code, int sallekey) throws FormateurNotFoundException, LienFormateurFormationException;
}
