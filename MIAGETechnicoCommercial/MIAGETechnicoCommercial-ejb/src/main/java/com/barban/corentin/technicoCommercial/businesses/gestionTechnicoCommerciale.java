/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.businesses;

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
import com.barban.corentin.technicoCommercial.repositories.FormateurcompetentFacade;
import com.barban.corentin.technicoCommercial.repositories.FormationcatalogueFacade;
import com.barban.corentin.technicoCommercial.repositories.SalleadequateFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jdetr
 */
@Stateless
public class gestionTechnicoCommerciale implements gestionTechnicoCommercialeLocal {

    @EJB 
    private FormateurcompetentFacade formateurF;
    
    @EJB
    private FormationcatalogueFacade formationF;
    
    @EJB
    private SalleadequateFacade salleF;

    @Override
    public Formationcatalogue ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueException {
        if (this.formationF.find(code) != null) {
            throw new FormationCatalogueException();
        }
        return null;
        
    }

    @Override
    public boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Formationcatalogue consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ajouterFormateurAFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Formateurcompetent> rechercherFormateursDeFormation(String code) throws FormationCatalogueNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ajouterSalleAFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimerSalleDeFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Salleadequate> rechercherSallesDeFormation(String code) throws FormationCatalogueNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Formationcatalogue> listerCatalogueFormations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validerExistenceFormation(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
