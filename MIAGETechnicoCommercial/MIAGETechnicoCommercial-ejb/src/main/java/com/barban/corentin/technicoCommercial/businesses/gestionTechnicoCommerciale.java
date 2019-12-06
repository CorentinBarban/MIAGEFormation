/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.businesses;

import DTO.FormateurDTO;
import DTO.FormationDTO;
import DTO.SalleDTO;
import Exceptions.FormateurNotFoundException;
import Exceptions.FormationCatalogueException;
import Exceptions.FormationCatalogueNotFoundException;
import Exceptions.LienFormateurFormationException;
import Exceptions.LienFormateurFormationNotFoundException;
import Exceptions.LienSalleFormationException;
import Exceptions.LienSalleFormationNotFoundException;
import Exceptions.SalleNotFoundException;
import com.barban.corentin.technicoCommercial.entities.Formateurcompetent;
import com.barban.corentin.technicoCommercial.entities.Formationcatalogue;
import com.barban.corentin.technicoCommercial.entities.Salleadequate;
import com.barban.corentin.technicoCommercial.repositories.FormateurcompetentFacadeLocal;
import com.barban.corentin.technicoCommercial.repositories.FormationcatalogueFacadeLocal;
import com.barban.corentin.technicoCommercial.repositories.SalleadequateFacadeLocal;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jdetr
 */
@Stateless
public class gestionTechnicoCommerciale implements gestionTechnicoCommercialeLocal {
    
    @EJB    
    private FormateurcompetentFacadeLocal formateurF;
    
    @EJB
    private FormationcatalogueFacadeLocal formationF;
    
    @EJB
    private SalleadequateFacadeLocal salleF;
    
    final String hostPatrimoine = "http://localhost:8085/MIAGEPatrimoine-web/webresources";
    final String hostRH = "http://localhost:8085/MIAGERessourcesHumaines-web/webresources";
    
    @Override
    public Formationcatalogue ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueException {
        if (this.formationF.findByCode(code) != null) {
            throw new FormationCatalogueException();
        }
        Formationcatalogue fc = new Formationcatalogue(code, intitule, niveau, typeduree, capacitemin, capacitemax, tarifforfaitaire);
        Formationcatalogue retour = this.formationF.create(fc);        
        return retour;
    }
    
    @Override
    public boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.findByCode(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.findByCode(code);
        this.formationF.remove(fc);
        return true;
    }

    // go commercial
    @Override
    public FormationDTO consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.findByCode(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.findByCode(code);
        FormationDTO fDTO = new FormationDTO(fc.getIntitule(), fc.getCode(), fc.getNiveau(), fc.getTypeduree(), fc.getCapacitemin(), fc.getCapacitemax(), fc.getTarifforfaitaire());
        return fDTO;
    }
    
    @Override
    public boolean ajouterFormateurAFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationException {
        try {
            if (this.formationF.findByCode(code) == null) {
                throw new FormationCatalogueNotFoundException();
            }
            URL url = new URL(hostRH + "/formateurs");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            ArrayList<FormateurDTO> listeFormateurs = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    FormateurDTO formateur = gson.fromJson(output, FormateurDTO.class);
                    listeFormateurs.add(formateur);
                }
                boolean testFormateur = false;
                for (FormateurDTO f : listeFormateurs) {
                    if (f.getIdFormateur() == formateurkey) {
                        testFormateur = true;
                    }
                }
                if (testFormateur) {
                Formationcatalogue fc = this.formationF.findByCode(code);
                Collection<Formateurcompetent> formateurs = fc.getFormateurcompetentCollection();
                Formateurcompetent formateur = new Formateurcompetent(formateurkey);
                if (formateurs.contains(formateur)) {
                    throw new LienFormateurFormationException();
                }
                this.formateurF.create(formateur);
                formateurs.add(formateur);
                fc.setFormateurcompetentCollection(formateurs);
                this.formationF.edit(fc);
                conn.disconnect();
                return true;
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationNotFoundException {
        try {
            if (this.formationF.findByCode(code) == null) {
                throw new FormationCatalogueNotFoundException();
            }
            URL url = new URL(hostRH + "/formateurs");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            ArrayList<FormateurDTO> listeFormateurs = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    FormateurDTO formateur = gson.fromJson(output, FormateurDTO.class);
                    listeFormateurs.add(formateur);
                }
                boolean testFormateur = false;
                for (FormateurDTO f : listeFormateurs) {
                    if (f.getIdFormateur() == formateurkey) {
                        testFormateur = true;
                    }
                }
                if (testFormateur) {
                    Formationcatalogue fc = this.formationF.findByCode(code);
                    Collection<Formateurcompetent> formateurs = fc.getFormateurcompetentCollection();
                    Formateurcompetent formateur = new Formateurcompetent(formateurkey);
                    if (!formateurs.contains(formateur)) {
                        throw new LienFormateurFormationNotFoundException();
                    }
                    formateurs.remove(formateur);
                    fc.setFormateurcompetentCollection(formateurs);
                    this.formationF.edit(fc);
                    return true;
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<FormateurDTO> rechercherFormateursDeFormation(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.findByCode(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.findByCode(code);
        Collection<Formateurcompetent> formateurs = fc.getFormateurcompetentCollection();
        List<FormateurDTO> formateursDTO = new ArrayList<>();
        for (Formateurcompetent formateur : formateurs) {
            FormateurDTO f = new FormateurDTO();
            f.setIdFormateur(formateur.getFormateurkey());
            formateursDTO.add(f);
        }
        return formateursDTO;
    }
    
    @Override
    public boolean ajouterSalleAFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationException {
        try {
            if (this.formationF.find(code) == null) {
                throw new FormationCatalogueNotFoundException();
            }
            URL url = new URL(hostPatrimoine + "/salles");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            ArrayList<SalleDTO> listeSalles = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    SalleDTO salle = gson.fromJson(output, SalleDTO.class);
                    listeSalles.add(salle);
                }
                boolean testSalle = false;
                for (SalleDTO s : listeSalles) {
                    if (s.getIdsalle() == sallekey) {
                        testSalle = true;
                    }
                }
                if (testSalle) {
                    Formationcatalogue fc = this.formationF.findByCode(code);
                    Collection<Salleadequate> salles = fc.getSalleadequateCollection();
                    Salleadequate salle = new Salleadequate(sallekey);
                    if (salles.contains(salle)) {
                        throw new LienSalleFormationException();
                    }
                    salles.add(salle);
                    fc.setSalleadequateCollection(salles);
                    this.formationF.edit(fc);
                    return true;
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean supprimerSalleDeFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationNotFoundException {
        try {
            if (this.formationF.findByCode(code) == null) {
                throw new FormationCatalogueNotFoundException();
            }
            URL url = new URL(hostPatrimoine + "/salles");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            ArrayList<SalleDTO> listeSalles = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    SalleDTO salle = gson.fromJson(output, SalleDTO.class);
                    listeSalles.add(salle);
                }
                boolean testSalle = false;
                for (SalleDTO s : listeSalles) {
                    if (s.getIdsalle() == sallekey) {
                        testSalle = true;
                    }
                }
                if (testSalle) {
                    Formationcatalogue fc = this.formationF.findByCode(code);
                    Collection<Salleadequate> salles = fc.getSalleadequateCollection();
                    Salleadequate salle = new Salleadequate(sallekey);
                    if (!salles.contains(salle)) {
                        throw new LienSalleFormationNotFoundException();
                    }
                    salles.remove(salle);
                    fc.setSalleadequateCollection(salles);
                    this.formationF.edit(fc);
                    return true;
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gestionTechnicoCommerciale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<SalleDTO> rechercherSallesDeFormation(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.findByCode(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.findByCode(code);
        Collection<Salleadequate> salles = fc.getSalleadequateCollection();
        List<SalleDTO> sallesDTO = new ArrayList<SalleDTO>();
        
        for (Salleadequate salle : salles) {
            SalleDTO s = new SalleDTO();
            s.setIdsalle(salle.getSallekey());
            sallesDTO.add(s);
        }
        return sallesDTO;
    }
    
    @Override
    public List<FormationDTO> listerCatalogueFormations() {
        List<Formationcatalogue> catalogue = this.formationF.findAll();
        List<FormationDTO> catalogueDTO = new ArrayList<>();
        for (Formationcatalogue f : catalogue) {
            FormationDTO fDTO = new FormationDTO(f.getIntitule(), f.getCode(), f.getNiveau(), f.getTypeduree(), f.getCapacitemin(), f.getCapacitemax(), f.getTarifforfaitaire());
            catalogueDTO.add(fDTO);
        }
        return catalogueDTO;
    }
    
}
