/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.services;

import DTO.CompteRenduDTO;
import Exceptions.FormationCatalogueNotFoundException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface serviceGestionCommercialeLocal {

    void demanderFormation(String nomClient,String codeFormation, String intitule ,Integer codeclient,Integer nbPersonnes) throws FormationCatalogueNotFoundException;

    void demandeEditionComptesRendus();
    
}
