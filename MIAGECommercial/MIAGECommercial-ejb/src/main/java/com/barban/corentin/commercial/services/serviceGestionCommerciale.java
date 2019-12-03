/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.services;

import DTO.CompteRenduDTO;
import DTO.DemandeFormationDTO;
import DTO.FormateurDTO;
import DTO.FormationDTO;
import DTO.SalleDTO;
import Exceptions.FormationCatalogueNotFoundException;
import Exceptions.ListeFormationsVideException;
import com.barban.corentin.commercial.business.gestionCommercialeLocal;
import com.barban.corentin.commercial.sender.SenderDemandeFormationJMS;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    
    /**
     * Realiser une demande de formation
     * @param nomClient Nom du client
     * @param codeFormation Code de la formation
     * @param intitule Intitule de la formation
     * @param codeclient Code du client
     * @param nbPersonnes Nombre de personnes demandées pour la formation
     * @throws FormationCatalogueNotFoundException 
     */
    @Override
    public void demanderFormation(String nomClient,String codeFormation, String intitule ,Integer codeclient,Integer nbPersonnes) throws FormationCatalogueNotFoundException{
        //Mémorisation de la demande de formation
        Date dateDemande = new Date();
        this.gestionCommerciale.memoriserDemandeFormation(nomClient, dateDemande, codeFormation, intitule, codeclient);
        //Validation de la demande de formation
        boolean existenceFormation = this.gestionCommerciale.validerExistenceFormation(codeFormation);
        if(existenceFormation == true){
            // Recuperer la liste de formateur pressentis
            List<FormateurDTO> listeFormateur = this.gestionCommerciale.recupererListeFormateurCompetent(codeFormation);
           // Recuperer la liste de salles pressenties
            List<SalleDTO> listeSalles = this.gestionCommerciale.recupererListeSallesAdequates(codeFormation);
            
            FormationDTO formationCatalogue = this.gestionCommerciale.recupererInformationFormationCatalogue(codeFormation);
            Integer capaciteMax = formationCatalogue.getCapacitemax();
            Integer capaciteMin = formationCatalogue.getCapacitemin();
            String typeFormation = formationCatalogue.getTypeduree();
            
            DemandeFormationDTO df = new DemandeFormationDTO();
            df.setCodeClient(codeclient);
            df.setNomClient(nomClient);
            df.setCodeFormation(codeFormation);
            df.setListFormateursPressentis(listeFormateur);
            df.setListSallesPressenties(listeSalles);
            df.setIntitule(intitule);
            df.setNbPersonnes(nbPersonnes);
            df.setCapaciteMax(capaciteMax);
            df.setCapaciteMin(capaciteMin);
            df.setTypeFormation(typeFormation);
            SenderDemandeFormationJMS sender = new SenderDemandeFormationJMS();
            sender.sendMessageDemandeFormation(df);
        }else{
            throw new FormationCatalogueNotFoundException();
        }        
    }

    @Override
    public void demandeEditionComptesRendus() {
        try {
            this.gestionCommerciale.editerComptesRendus();
        } catch (ListeFormationsVideException ex) {
            Logger.getLogger(serviceGestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
}
