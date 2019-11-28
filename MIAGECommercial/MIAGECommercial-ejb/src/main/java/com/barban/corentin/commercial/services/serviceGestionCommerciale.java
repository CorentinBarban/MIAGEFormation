/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.services;

import DTO.DemandeFormationDTO;
import DTO.FormateurDTO;
import DTO.SalleDTO;
import Exceptions.FormationCatalogueNotFoundException;
import com.barban.corentin.commercial.business.gestionCommercialeLocal;
import com.barban.corentin.commercial.sender.SenderDemandeFormationJMS;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Corentin
 */
@Stateless
public class serviceGestionCommerciale implements serviceGestionCommercialeLocal {

    @EJB
    private gestionCommercialeLocal gestionCommerciale;

    
    
    @Override
    public void demanderFormation(String nomClient,String codeFormation, String intitule ,Integer codeclient, Date dateFormation,Integer nbPersonnes) throws FormationCatalogueNotFoundException{
        //Mémorisation de la demande de formation
        Date dateDemande = new Date();
        this.gestionCommerciale.memoriserDemandeFormation(nomClient, dateDemande, codeFormation, intitule, codeclient);
        //Validation de la demande de formation
        boolean existenceFormation = this.gestionCommerciale.validerExistenceFormation(codeFormation);
        if(existenceFormation == true){
            // Recuperer la liste de formateur pressentis
            List<FormateurDTO> listeFormateur = this.gestionCommerciale.recupererListeFormateurCompetent(codeFormation);
            System.out.println(listeFormateur.toString());
            // Recuperer la liste de salles pressenties
            List<SalleDTO> listeSalles = this.gestionCommerciale.recupererListeSallesAdequates(codeFormation);
            
            DemandeFormationDTO df = new DemandeFormationDTO();
            df.setCodeClient(codeclient);
            df.setNomClient(nomClient);
            df.setCodeFormation(codeFormation);
            df.setDate(dateFormation);
            df.setListFormateursPressentis(listeFormateur);
            df.setListSallesPressenties(listeSalles);
            df.setIntitule(intitule);
            df.setNbPersonnes(nbPersonnes);
            SenderDemandeFormationJMS sender = new SenderDemandeFormationJMS();
            sender.sendMessageDemandeFormation(df);
        }else{
            throw new FormationCatalogueNotFoundException();
        }        
    }
  
    
}
