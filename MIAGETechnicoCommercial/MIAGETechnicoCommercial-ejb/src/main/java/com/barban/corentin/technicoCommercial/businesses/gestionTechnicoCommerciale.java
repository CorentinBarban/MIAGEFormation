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
import java.util.Collection;
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
        Formationcatalogue fc = new Formationcatalogue(code, intitule, niveau, typeduree, capacitemin, capacitemax, tarifforfaitaire);
        Formationcatalogue retour = this.formationF.create(fc); 
        return retour;
    }

    @Override
    public boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.find(code);
        this.formationF.remove(fc);
        return true;
    }

    @Override
    public Formationcatalogue consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.find(code);
        return fc;
    }

    @Override
    public boolean ajouterFormateurAFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        //ajouter la verification via interrogation a gestion RH
        Formationcatalogue fc = this.formationF.find(code);
        Collection<Formateurcompetent> formateurs = fc.getFormateurcompetentCollection();
        Formateurcompetent formateur = new Formateurcompetent(formateurkey);
        if (formateurs.contains(formateur)) {
            throw new LienFormateurFormationException();
        }
        formateurs.add(formateur);
        fc.setFormateurcompetentCollection(formateurs);
        this.formationF.edit(fc);
        return true;
    }

    @Override
    public boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationNotFoundException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        //ajouter la verification via interrogation a gestion RH
        Formationcatalogue fc = this.formationF.find(code);
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

    @Override
    public Collection<Formateurcompetent> rechercherFormateursDeFormation(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.find(code);
        Collection<Formateurcompetent> formateurs = fc.getFormateurcompetentCollection();
        return formateurs;
    }

    @Override
    public boolean ajouterSalleAFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        //ajouter la verification via interrogation a gestion Patrimoine
        Formationcatalogue fc = this.formationF.find(code);
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

    @Override
    public boolean supprimerSalleDeFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationNotFoundException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        //ajouter la verification via interrogation a gestion Patrimoine
        Formationcatalogue fc = this.formationF.find(code);
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

    @Override
    public Collection<Salleadequate> rechercherSallesDeFormation(String code) throws FormationCatalogueNotFoundException {
        if (this.formationF.find(code) == null) {
            throw new FormationCatalogueNotFoundException();
        }
        Formationcatalogue fc = this.formationF.find(code);
        Collection<Salleadequate> salles = fc.getSalleadequateCollection();
        return salles;
    }

    @Override
    public List<Formationcatalogue> listerCatalogueFormations() {
        List<Formationcatalogue> catalogue = this.formationF.findAll();
        return catalogue;
    }

    @Override
    public boolean validerExistenceFormation(String code) {
        if (this.formationF.find(code) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    
}
