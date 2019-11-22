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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Classe métier de la gestion commerciale
 *
 * @author Mathieu Stivanin
 */

    @Stateless
    public class gestionCommerciale implements gestionCommercialeLocal {

    @EJB
    private DemandedeformationFacadeLocal demandedeformationFacade;

    /**
     * Méthode permettant de récupérer le catalogue de formations.
     *
     * @return Une liste de formations DTO ??
     */
    
    @Override
    public List<FormationDTO> recupererCatalogueFormations() {
        
        try {
            URL url = new URL("/formationsCatalogue");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Liste des formations présentes dans la catalogue : \n"); // Ici, la récupération du catalogueFormation sera faite lorsque ce sera implémenté côté TC
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();            
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    /**
     * Méthode permettant de mémoriser une demande de formation
     *
     * @param nomClient
     * @param dateDemande
     * @param codeFormation
     * @param intituleFormation
     * @param codeclient
     */
    
    @Override
    public void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, int codeclient) {
        Demandedeformation demandeFormation = new Demandedeformation(nomClient, dateDemande, codeFormation, intituleFormation, codeclient);
        Demandedeformation dfObjet = this.demandedeformationFacade.create(demandeFormation);
    }

    /**
     * Méthode permettant de générer des comptes rendus, positifs ou négatifs.
     *
     * @return Une objet CompteRenduDTO représentant un compte rendu
     * @throws ListeFormationsVideException
     */
    
    @Override
    public CompteRenduDTO editerCompteRendus() throws ListeFormationsVideException { //Doit throw aussi CapaciteNotFound
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

    /**
     * Fonction permettant de valider l'existence d'une formation
     *
     * @param code code de la formation
     * @return true si la formation existe, false si la formation n'existe pas
     */
    @Override
    public boolean validerExistenceFormation(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Récupérer toutes les formations en REST via le catalogue, et vérifier si elle existe. Si elle existe, return true.
    }

    /**
     * Méthode permettant de demander l'état d'une formation
     *
     * @param idFormation l'id de la formation souhaitée
     * @return une chaine contenant l'état de la formation
     */
    
    @Override
    public String demanderEtatFormation(int idFormation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Appel REST
    }

    //Traitement REST : PostMan, et RESFTful Web Service Pattern
    //Créer FormationResource pour le GET de l'état de la formation par le client
    //Créer DemandeDeFormationResource pour l'interaction Commercial/GC
    //Créer FormationCatalogueResource pour GET le catalogue de la part du TC
}
