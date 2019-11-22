/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.services;

import DTO.FormationDTO;
import Exceptions.FormationCatalogueNotFoundException;
import com.barban.corentin.technicoCommercial.businesses.gestionTechnicoCommerciale;
import com.barban.corentin.technicoCommercial.businesses.gestionTechnicoCommercialeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jdetr
 */
@Stateless
public class ServiceTechnicoCommercial implements ServiceTechnicoCommercialLocal {

    @EJB
    private gestionTechnicoCommercialeLocal gestionTC;
    
    @Override
    public FormationDTO consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
            return this.gestionTC.consulterFormationCatalogue(code);
    }
    
    @Override
    public List<FormationDTO> listerCatalogueFormations() {
            return this.gestionTC.listerCatalogueFormations();
    }
    
}
