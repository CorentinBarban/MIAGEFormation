/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.services;

import DTO.DemandeFormationDTO;
import Exceptions.FormationCatalogueNotFoundException;
import com.barban.corentin.commercial.business.gestionCommercialeLocal;
import com.barban.corentin.commercial.sender.senderDemandeFormationJMS;
import java.util.Date;
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
    public void demanderFormation(String nomClient,String codeFormation, String intitule ,Integer codeclient, Date dateFormation) throws FormationCatalogueNotFoundException{
        //Mémorisation de la demande de formation
        Date dateDemande = new Date();
        this.gestionCommerciale.memoriserDemandeFormation(nomClient, dateDemande, codeFormation, intitule, codeclient);
        
        //Validation de la demande de formation
        boolean existenceFormation = this.gestionCommerciale.validerExistenceFormation(codeFormation);
        if(existenceFormation == true){
            DemandeFormationDTO df = new DemandeFormationDTO();
            df.setCodeClient(codeclient);
            df.setCodeFormation(codeFormation);
            df.setDate(dateFormation);
            senderDemandeFormationJMS sender = new senderDemandeFormationJMS();
            sender.sendMessageDemandeFormation(df);
        }else{
            throw new FormationCatalogueNotFoundException();
        }        
    }
  
    
}
