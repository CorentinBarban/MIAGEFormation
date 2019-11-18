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
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jdetr
 */
@Local
public interface gestionTechnicoCommercialeLocal {
    
    /**
     * Ajouter une formation au catalogue
     * @param code
     * @param intitule
     * @param niveau
     * @param typeduree
     * @param capacitemin
     * @param capacitemax
     * @param tarifforfaitaire
     * @return la formation créée
     * @throws FormationCatalogueException 
     */
    Formationcatalogue ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueException ; 
 
    /**
     * Supprimer une formation du catalogue
     * @param code
     * @return booléen de vérification
     * @throws FormationCatalogueNotFoundException 
     */
    boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException ;
    
    /**
     * Rechercher une formation dans le catalogue
     * @param code
     * @return la formation trouvée
     * @throws FormationCatalogueNotFoundException 
     */
    Formationcatalogue consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException ; 

    /**
     * Ajouter un formateur compétent pour une formation
     * @param formateurkey
     * @param code
     * @return booléen de vérification
     * @throws LienFormateurFormationException 
     * @throws FormationCatalogueNotFoundException
     * @throws FormateurNotFoundException
     */
    boolean ajouterFormateurAFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationException ;
   
    /**
     * Supprimer un formateur d'une formation
     * @param formateurkey
     * @param code
     * @return booléen de vérification
     * @throws LienFormateurFormationNotFoundException 
     * @throws FormationCatalogueNotFoundException
     * @throws FormateurNotFoundException
     */
    boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationNotFoundException ;
   
    /**
     * Lister les formateurs compétents pour une formation donnée
     * @param code
     * @return la liste des formateurs compétents pour la formation donnée
     * @throws FormationCatalogueNotFoundException
     */
    Collection<Formateurcompetent> rechercherFormateursDeFormation(String code) throws FormationCatalogueNotFoundException ;

        /**
     * Ajouter une salle adéquate pour une formation
     * @param sallekey
     * @param code
     * @return booléen de vérification
     * @throws LienSalleFormationException 
     * @throws FormationCatalogueNotFoundException
     * @throws SalleNotFoundException
     */
    boolean ajouterSalleAFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationException ;
   
    /**
     * Supprimer une salle d'une formation
     * @param sallekey
     * @param code
     * @return booléen de vérification
     * @throws LienSalleFormationNotFoundException 
     * @throws FormationCatalogueNotFoundException
     * @throws SalleNotFoundException
     */
    boolean supprimerSalleDeFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationNotFoundException ;
   
    /**
     * Lister les salles adéquates pour une formation donnée
     * @param code
     * @return la liste des salles adéquates pour la formation donnée
     * @throws FormationCatalogueNotFoundException
     */
    Collection<Salleadequate> rechercherSallesDeFormation(String code) throws FormationCatalogueNotFoundException ;

    /**
     * Lister les formations du catalogue
     * @return la liste des formations du catalogue
     */
    List<Formationcatalogue> listerCatalogueFormations();
    
    /**
     * Vérifier si une formation donnée est dans le catalogue
     * @param code
     * @return booléen de réponse
     */
    boolean validerExistenceFormation(String code);
    
}
