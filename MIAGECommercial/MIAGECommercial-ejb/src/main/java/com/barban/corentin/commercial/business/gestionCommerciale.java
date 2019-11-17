/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import DTO.CompteRenduDTO;
import DTO.FormationDTO;
import Exceptions.ListeFormationsVideException;
import com.barban.corentin.commercial.entities.Demandedeformation;
import com.barban.corentin.commercial.repositories.DemandedeformationFacadeLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author maths
 */
@Stateless
public class gestionCommerciale implements gestionCommercialeLocal {

    @EJB
    private DemandedeformationFacadeLocal demandedeformationFacade;

    @Override
    public List<FormationDTO> recupererCatalogueFormations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, int codeclient) {
        Demandedeformation demandeFormation = new Demandedeformation(nomClient, dateDemande, codeFormation, intituleFormation, codeclient);
        Demandedeformation dfObjet = this.demandedeformationFacade.create(demandeFormation);
    }

    @Override 
    public CompteRenduDTO editerCompteRendus() throws ListeFormationsVideException {
        List<FormationDTO> listeFormations = new ArrayList<FormationDTO>();
        CompteRenduDTO compteRendu = null;
        //traitement et récupération de la liste de formations
        if (listeFormations.size() > 0) {
            for (int i = 0; i < listeFormations.size(); i++) {
                FormationDTO formation = listeFormations.get(i);
                int nbPersonnes = formation.getNbpersonne();
                int capaciteMin = 0;//TO-DO
                Calendar calendier = new GregorianCalendar();
                calendier.add(formation.getDateformation().getDate(), 30);
                Date dateJour30 = calendier.getTime();
                Date jour = new Date();
                //Ici, on doit aller taper dans technico-commercial pour chopper la clé de la formation en question dans le formationcatalogue pour avoir la capacite (REST)
                //Récupérer keycatalogue de la formation, check dans le REST de TC pour avoir la capacité correspondante
                //Traitement REST : PostMan, et RESFTful Web Service Pattern
                //Créer FormationResource pour le GET de l'état de la formation par le client
                //Créer DemandeDeFormationResource pour l'interaction Commercial/GC
                //Créer FormationCatalogueResource pour GET le catalogue de la part du TC
                if (nbPersonnes < capaciteMin && jour == dateJour30) {
                    compteRendu = new CompteRenduDTO(formation.getIntitule(), formation.getDateformation(), formation.getNomclient(), "Négatif", formation.getNbpersonne());
                } else {
                    compteRendu = new CompteRenduDTO(formation.getIntitule(), formation.getDateformation(), formation.getNomclient(), "Positif", formation.getNbpersonne());
                }

            }
        } else {
            throw new ListeFormationsVideException();
        }
        return compteRendu;
    }

    @Override
    public String demanderStatutFormation(int idFormation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validerExistenceFormation(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Si formation existe
        //Else
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public CompteRenduDTO creerCompteRendu(FormationDTO formation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
