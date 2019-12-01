/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.business;

import DTO.CompteRenduDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface GestionFormationLocal {
    
    Formation demanderFormation(String nomClient,Integer nbPersonne,String codeFormation,Stockagedemandeformation keyStockageDemandeFormation);
    
    Stockagedemandeformation stockerDemande(String codeFormation,String intitule,Integer codeClient);

    String demanderEtatFormation(Integer idFormation);

    List<CompteRenduDTO> retournerCompteRendus();

    void ajouterFormateurFormation(int idFormation,int idFormateur);

    void ajouterSalleFormation(int idFormation,int idSalle);

    
    
}
