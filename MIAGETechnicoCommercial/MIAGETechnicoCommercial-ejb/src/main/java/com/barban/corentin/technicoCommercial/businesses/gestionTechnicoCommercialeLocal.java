/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.businesses;

import com.barban.corentin.technicoCommercial.entities.Formateurcompetent;
import com.barban.corentin.technicoCommercial.entities.Formationcatalogue;
import com.barban.corentin.technicoCommercial.entities.Salleadequate;
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
     * @throws PrimaryKeyFormationCatalogueException 
     */
    Formationcatalogue ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire); //throws PrimaryKeyFormationCatalogueException ; 
 
    /**
     * Supprimer une formation du catalogue
     * @param code
     * @return booléen de vérification
     * @throws NotFoundException 
     */
    boolean supprimerFormationCatalogue(String code); //throws NotFoundException ;
    
    /**
     * Rechercher une formation dans le catalogue
     * @param code
     * @return la formation trouvée
     * @throws NotFoundException 
     */
    Formationcatalogue consulterFormationCatalogue(String code); //throws NotFoundException ; 

    /**
     * Ajouter un formateur compétent pour une formation
     * @param formateurkey
     * @param code
     * @return booléen de vérification
     * @throws LienFormateurFormationException 
     * @throws NotFoundException
     * @throws FormateurNotFoundException
     */
    boolean ajouterFormateurAFormation(String code, int formateurkey); //throws NotFoundException, FormateurNotFoundException, LienFormateurFormationException ;
   
    /**
     * Supprimer un formateur d'une formation
     * @param formateurkey
     * @param code
     * @return booléen de vérification
     * @throws LienFormateurFormationNotFoundException 
     * @throws NotFoundException
     * @throws FormateurNotFoundException
     */
    boolean supprimerFormateurDeFormation(String code, int formateurkey); //throws NotFoundException, FormateurNotFoundException, LienFormateurFormationNotFoundException ;
   
    /**
     * Lister les formateurs compétents pour une formation donnée
     * @param code
     * @return la liste des formateurs compétents pour la formation donnée
     * @throws NotFoundException
     */
    List<Formateurcompetent> rechercherFormateursDeFormation(String code); //throws NotFoundException ;

        /**
     * Ajouter une salle adéquate pour une formation
     * @param sallekey
     * @param code
     * @return booléen de vérification
     * @throws LienSalleFormationException 
     * @throws NotFoundException
     * @throws SalleNotFoundException
     */
    boolean ajouterSalleAFormation(String code, int sallekey); //throws NotFoundException, SalleNotFoundException, LienSalleFormationException ;
   
    /**
     * Supprimer une salle d'une formation
     * @param sallekey
     * @param code
     * @return booléen de vérification
     * @throws LienSalleFormationNotFoundException 
     * @throws NotFoundException
     * @throws SalleNotFoundException
     */
    boolean supprimerSalleDeFormation(String code, int sallekey); //throws NotFoundException, SalleNotFoundException, LienSalleFormationNotFoundException ;
   
    /**
     * Lister les salles adéquates pour une formation donnée
     * @param code
     * @return la liste des salles adéquates pour la formation donnée
     * @throws NotFoundException
     */
    List<Salleadequate> rechercherSallesDeFormation(String code); //throws NotFoundException ;

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
