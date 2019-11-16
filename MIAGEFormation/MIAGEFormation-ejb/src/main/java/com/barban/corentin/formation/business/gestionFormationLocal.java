/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import DTO.CompteRenduDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface gestionFormationLocal {
    
    void demanderFormation();
    
    void stockerDemande();

    String demanderEtatFormation(Integer idFormation);

    List<CompteRenduDTO> retournerCompteRendus();

    
    
}
