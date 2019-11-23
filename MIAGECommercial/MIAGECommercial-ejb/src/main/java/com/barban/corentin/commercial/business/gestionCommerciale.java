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
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

    private List<FormationDTO> listeFormations;
    
    final String host = "http://localhost:8085/MIAGETechnicoCommercial-web/webresources/";
    /**
     * Méthode permettant de récupérer le catalogue de formations.
     *
     */
    @Override
    public void recupererCatalogueFormations() {
        try {
            URL url = new URL("/formationsCatalogue");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            this.listeFormations = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    FormationDTO formation = gson.fromJson(output, FormationDTO.class);
                    this.listeFormations.add(formation);
                    formation.toString();
                }
            }
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, Integer codeclient) {
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
    public CompteRenduDTO editerCompteRendus() throws ListeFormationsVideException {

        //Refaire appel REST pour récupérer + stocker la liste de formations à partir du catalogue
        CompteRenduDTO compteRendu = null;

        try {
            URL url = new URL("/formationsCatalogue");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            this.listeFormations = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    FormationDTO formation = gson.fromJson(output, FormationDTO.class);
                    this.listeFormations.add(formation);
                }
            }
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listeFormations.size() > 0) {
            for (int i = 0; i < listeFormations.size(); i++) {
                FormationDTO formation = listeFormations.get(i);
                int nbPersonnes = formation.getNbpersonne();
                int capaciteMin = formation.getCapacitemin();
                Calendar calendier = new GregorianCalendar();
                calendier.add(formation.getDateformation().getDate(), 30);
                Date dateJour30 = calendier.getTime();
                Date jour = new Date();
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
    public boolean validerExistenceFormation(String code) {
        boolean existe = false;
        try {
           
            URL url = new URL(host + "formationsCatalogue/" + code);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                if ((output = br.readLine()) != null) {
                    existe = true;
                }
                conn.disconnect();
            }
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    /**
     * Méthode permettant de demander l'état d'une formation
     *
     * @param idFormation l'id de la formation souhaitée
     * @return une chaine contenant l'état de la formation
     */
    @Override
    public String demanderEtatFormation(int idFormation) {
        String etat = null;
        FormationDTO formation = null;
        try {
            URL url = new URL("/formations/" + idFormation);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    formation = gson.fromJson(output, FormationDTO.class);
                }
                etat = formation.getStatut();
            }
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }

        return etat;
    }

}
