/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.services;

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
import com.barban.corentin.technicoCommercial.businesses.gestionTechnicoCommerciale;
import com.barban.corentin.technicoCommercial.businesses.gestionTechnicoCommercialeLocal;
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
public class ServiceTechnicoCommercial implements ServiceTechnicoCommercialLocal {

    @EJB
    private gestionTechnicoCommercialeLocal gestionTC;
    
    @Override
    public FormationDTO consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
            return this.gestionTC.consulterFormationCatalogue(code);
    }
    
    @Override
    public FormationDTO ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueNotFoundException, FormationCatalogueException {
            return this.gestionTC.ajouterFormationCatalogue(code, intitule, niveau, typeduree, capacitemin, capacitemax, tarifforfaitaire);
    }
    
    /**
     *
     * @param code
     * @return
     * @throws FormationCatalogueNotFoundException
     */
    @Override
    public boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
            return this.gestionTC.supprimerFormationCatalogue(code);
    }
    
    @Override
    public List<FormationDTO> listerCatalogueFormations() {
            return this.gestionTC.listerCatalogueFormations();
    }

    @Override
    public List<SalleDTO> rechercherSallesAdequates(String code) {
        try {
            return this.gestionTC.rechercherSallesDeFormation(code);
        } catch (FormationCatalogueNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<FormateurDTO> rechercherFormateurAdequats(String code) {
        try {
            return this.gestionTC.rechercherFormateursDeFormation(code);
        } catch (FormationCatalogueNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean ajouterFormateurDansFormation(String code, int formateurkey) throws FormateurNotFoundException, LienFormateurFormationException {
        try {
            return this.gestionTC.ajouterFormateurAFormation(code, formateurkey);
        } catch (FormationCatalogueNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormateurNotFoundException, LienFormateurFormationException {
        try {
            return this.gestionTC.supprimerFormateurDeFormation(code, formateurkey);
        } catch (FormationCatalogueNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LienFormateurFormationNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean ajouterSalleDansFormation(String code, int sallekey) throws FormateurNotFoundException, LienFormateurFormationException {
        try {
            return this.gestionTC.ajouterSalleAFormation(code, sallekey);
        }   catch (FormationCatalogueNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SalleNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LienSalleFormationException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
    
    @Override
    public boolean supprimerSalleDeFormation(String code, int sallekey) throws FormateurNotFoundException, LienFormateurFormationException {
        try {
            return this.gestionTC.supprimerSalleDeFormation(code, sallekey);
        }   catch (FormationCatalogueNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SalleNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LienSalleFormationNotFoundException ex) {
            Logger.getLogger(ServiceTechnicoCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
}
