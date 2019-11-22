/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.services;

import DTO.FormationDTO;
import Exceptions.FormationCatalogueNotFoundException;
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
}
