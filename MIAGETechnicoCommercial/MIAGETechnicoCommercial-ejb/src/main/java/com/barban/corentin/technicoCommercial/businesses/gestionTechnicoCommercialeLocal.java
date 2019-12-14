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
import com.barban.corentin.technicoCommercial.entities.Formationcatalogue;
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
     * @param code code 
     * @param intitule intitule
     * @param niveau niveau
     * @param typeduree type de durée
     * @param capacitemin capacité minimum
     * @param capacitemax capacité maximim
     * @param tarifforfaitaire tarif forfaitaire
     * @return la formation créée
     * @throws FormationCatalogueException formation catalogue existante 
     */
    FormationDTO ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueException ; 
 
    /**
     * Supprimer une formation du catalogue
     * @param code code
     * @return booléen de vérification
     * @throws FormationCatalogueNotFoundException  formation catalogue non trouvé
     */
    boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException ;
    
    /**
     * Rechercher une formation dans le catalogue
     * @param code code
     * @return la formation trouvée
     * @throws FormationCatalogueNotFoundException  formation catalogue non trouvé
     */
    FormationDTO consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException ; 

    /**
     * Ajouter un formateur compétent pour une formation
     * @param formateurkey key formateur
     * @param code code
     * @return booléen de vérification
     * @throws LienFormateurFormationException Lien formateur et formation non trouvé
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     * @throws FormateurNotFoundException formateur non trouvé
     */
    boolean ajouterFormateurAFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationException ;
   
    /**
     * Supprimer un formateur d'une formation
     * @param formateurkey key formateur
     * @param code code 
     * @return booléen de vérification
     * @throws LienFormateurFormationNotFoundException  Lien formateur et formation non trouvé
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     * @throws FormateurNotFoundException formateur non trouvé
     */
    boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormationCatalogueNotFoundException, FormateurNotFoundException, LienFormateurFormationNotFoundException ;
   
    /**
     * Lister les formateurs compétents pour une formation donnée
     * @param code code
     * @return la liste des formateurs compétents pour la formation donnée
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     */
    List<FormateurDTO> rechercherFormateursDeFormation(String code) throws FormationCatalogueNotFoundException ;

        /**
     * Ajouter une salle adéquate pour une formation
     * @param sallekey key salle
     * @param code code 
     * @return booléen de vérification
     * @throws LienSalleFormationException Lien formateur et formation non trouvé
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     * @throws SalleNotFoundException salle non trouvé 
     */
    boolean ajouterSalleAFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationException ;
   
    /**
     * Supprimer une salle d'une formation
     * @param sallekey key salle 
     * @param code code 
     * @return booléen de vérification
     * @throws LienSalleFormationNotFoundException Lien formateur et formation non trouvé
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     * @throws SalleNotFoundException salle non trouvé 
     */
    boolean supprimerSalleDeFormation(String code, int sallekey) throws FormationCatalogueNotFoundException, SalleNotFoundException, LienSalleFormationNotFoundException ;
   
    /**
     * Lister les salles adéquates pour une formation donnée
     * @param code code 
     * @return la liste des salles adéquates pour la formation donnée
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     */
    List<SalleDTO> rechercherSallesDeFormation(String code) throws FormationCatalogueNotFoundException ;

    /**
     * Lister les formations du catalogue
     * @return la liste des formations du catalogue
     */
    List<FormationDTO> listerCatalogueFormations();

    
}
