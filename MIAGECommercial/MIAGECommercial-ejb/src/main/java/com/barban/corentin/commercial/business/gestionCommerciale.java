/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import DTO.FormateurDTO;
import DTO.FormationDTO;
import DTO.SalleDTO;
import Exceptions.ListeFormationsVideException;
import com.barban.corentin.commercial.entities.Demandedeformation;
import com.barban.corentin.commercial.repositories.DemandedeformationFacadeLocal;
import com.barban.corentin.commercial.sender.SenderDemandeCompteRendu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

    final String hostTechnico = "http://localhost:8085/MIAGETechnicoCommercial-web/webresources";

   /**
    * Méthode permettant de récupérer le catalogue de formations.
    * 
    */
    @Override
    public void recupererCatalogueFormations() {
        try {
            URL url = new URL(hostTechnico + "/formationsCatalogue");
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
     * @param nomClient Nom d'un client
     * @param dateDemande Date de la demande
     * @param codeFormation Code de la formation
     * @param intituleFormation Intitule de la formation
     * @param codeclient Code du client
     */
    @Override
    public void memoriserDemandeFormation(String nomClient, Date dateDemande, String codeFormation, String intituleFormation, Integer codeclient) {
        Demandedeformation demandeFormation = new Demandedeformation(nomClient, dateDemande, codeFormation, intituleFormation, codeclient);
        Demandedeformation dfObjet = this.demandedeformationFacade.create(demandeFormation);
    }

   
    /**
     * Méthode permettant de générer des comptes rendus, positifs ou négatifs.
     * 
     * @throws ListeFormationsVideException ListeFormationsVideException liste formation vide 
     */
    @Override
    public void editerComptesRendus() throws ListeFormationsVideException {

        SenderDemandeCompteRendu sender = new SenderDemandeCompteRendu();
        sender.sendMessageDemandeInformationFormations();

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

            URL url = new URL(hostTechnico + "/formationsCatalogue/" + code);
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

    /**
     * Méthode permettant de recuperer la liste des formateurs compétent pour
     * une formation
     *
     * @param code Code de la formation
     * @return liste des formateurs compétent pour une formation 
     */
    @Override
    public List<FormateurDTO> recupererListeFormateurCompetent(String code) {
        List<FormateurDTO> listeFormateurDTOs = new ArrayList<>();
        try {
            URL url = new URL(hostTechnico + "/formationsCatalogue/" + code + "/formateurs");
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
                    listeFormateurDTOs = gson.fromJson(output, new TypeToken<List<FormateurDTO>>() {
                    }.getType());
                }
            }
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeFormateurDTOs;
    }

    /**
     * Méthode permettant de recuperer la liste des salles adequates pour une
     * formation
     *
     * @param code Code de la formation
     * @return lite des salles adequates pour une formation 
     */
    @Override
    public List<SalleDTO> recupererListeSallesAdequates(String code) {
        List<SalleDTO> listeSallesDTOs = new ArrayList<>();
        try {
            URL url = new URL(hostTechnico + "/formationsCatalogue/" + code + "/salles");
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
                    listeSallesDTOs = gson.fromJson(output, new TypeToken<List<SalleDTO>>() {
                    }.getType());
                }
            }
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeSallesDTOs;
    }

    /**
     * Obtenir la capacité min d'une formation
     * @param codeFormation Code de la formation
     * @return la capacité min d'une formation
     */
    @Override
    public FormationDTO recupererInformationFormationCatalogue(String codeFormation) {
        try {
            URL url = new URL(hostTechnico + "/formationsCatalogue/" + codeFormation);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            FormationDTO formationDTO = null;
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;

                while ((output = br.readLine()) != null) {
                    formationDTO = gson.fromJson(output, new TypeToken<FormationDTO>() {
                    }.getType());
                }

            }
            conn.disconnect();
            return formationDTO;
        } catch (IOException ex) {
            Logger.getLogger(gestionCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

}
