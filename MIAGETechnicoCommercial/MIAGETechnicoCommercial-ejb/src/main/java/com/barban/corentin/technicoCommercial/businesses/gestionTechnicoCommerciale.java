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
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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
    public FormationDTO ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueException {
        if (this.formationF.findByCode(code) != null) {
            throw new FormationCatalogueException();
        }
        Formationcatalogue fc = new Formationcatalogue(code, intitule, niveau, typeduree, capacitemin, capacitemax, tarifforfaitaire);
        Formationcatalogue retour = this.formationF.create(fc);
        FormationDTO fDTO = new FormationDTO(retour.getIntitule(), retour.getCode(), retour.getNiveau(), retour.getTypeduree(), retour.getCapacitemin(), retour.getCapacitemax(), retour.getTarifforfaitaire());
        return fDTO;
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
                Type typeMyType = new TypeToken<ArrayList<FormateurDTO>>() {
                }.getType();

                while ((output = br.readLine()) != null) {
                    listeFormateurs = gson.fromJson(output, typeMyType);
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
                    Formateurcompetent formateur = this.formateurF.findByKey(formateurkey);
                    if (formateur == null) {
                        formateur = new Formateurcompetent(formateurkey);
                        this.formateurF.create(formateur);
                    }
                    formateurs.add(formateur);
                    formateur.getFormationcatalogueCollection().add(fc);
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
                Type typeMyType = new TypeToken<ArrayList<FormateurDTO>>() {
                }.getType();

                while ((output = br.readLine()) != null) {
                    listeFormateurs = gson.fromJson(output, typeMyType);
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
                    Formateurcompetent formateur = this.formateurF.findByKey(formateurkey);
                    if (formateur == null) {
                        throw new FormateurNotFoundException();
                    } else {
                        formateurs.remove(formateur);
                        formateur.getFormationcatalogueCollection().remove(fc);
                        return true;
                    }
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
                Type typeMyType = new TypeToken<ArrayList<SalleDTO>>() {
                }.getType();

                while ((output = br.readLine()) != null) {
                    listeSalles = gson.fromJson(output, typeMyType);
                }

                boolean testSalle = false;
                for (SalleDTO s : listeSalles) {
                    if (s.getIdsalle() == sallekey) {
                        testSalle = true;
                    }
                }

                //Verifier existance dans salle adequate
                if (testSalle) {
                    Formationcatalogue formationCatalogue = this.formationF.findByCode(code);
                    Collection<Salleadequate> sallesAdequates = formationCatalogue.getSalleadequateCollection();
                    System.out.println("collection salle " + sallesAdequates.toString());
                    Salleadequate salle = this.salleF.findByKey(sallekey);
                    System.out.println("salle " + salle.toString());
                    if (salle == null) {
                        salle = new Salleadequate(sallekey);
                        this.salleF.create(salle);
                    }
                    sallesAdequates.add(salle);
                    salle.getFormationcatalogueCollection().add(formationCatalogue);
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
                Type typeMyType = new TypeToken<ArrayList<SalleDTO>>() {
                }.getType();

                while ((output = br.readLine()) != null) {
                    listeSalles = gson.fromJson(output, typeMyType);
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
                    Salleadequate salle = this.salleF.findByKey(sallekey);
                    if (salle == null) {
                        throw new SalleNotFoundException();
                    } else {
                        salles.remove(salle);
                        salle.getFormationcatalogueCollection().remove(fc);
                        return true;
                    }
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
