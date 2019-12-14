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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jdetr
 */
@Local
public interface ServiceTechnicoCommercialLocal {
    
    /**
     * Ajoute une formation au catalogue
     * @param code code 
     * @param intitule intitule
     * @param niveau niveau
     * @param typeduree type duree
     * @param capacitemin capacite minimum
     * @param capacitemax capacite maximum
     * @param tarifforfaitaire tarif forfaitaire
     * @return true si formation créer 
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé 
     * @throws FormationCatalogueException formation catalogue exception 
     */
    FormationDTO ajouterFormationCatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) throws FormationCatalogueNotFoundException, FormationCatalogueException;
    
    /**
     * Supprimer la formation catalogue
     * @param code code 
     * @return true si supprimé 
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     */
    boolean supprimerFormationCatalogue(String code) throws FormationCatalogueNotFoundException;
    
    /**
     * Consulter la formation catalogue
     * @param code code
     * @return la formation catalogue
     * @throws FormationCatalogueNotFoundException formation catalogue non trouvé
     */
    FormationDTO consulterFormationCatalogue(String code) throws FormationCatalogueNotFoundException;
    
    /**
     * lister formation catalogue 
     * @return le catalogue de formations
     */
    List<FormationDTO> listerCatalogueFormations();

    /**
     * Recherche la salle adequates
     * @param code code 
     * @return la salle adequates
     */
    List<SalleDTO> rechercherSallesAdequates(String code);

    /**
     * REcherche formateur adequats
     * @param code code
     * @return le formateur adequats
     */
    List<FormateurDTO> rechercherFormateurAdequats(String code);
    
    /**
     * Ajouter un formateur dans la formation 
     * @param code code 
     * @param formateurkey formateur key
     * @return true si bien créer 
     * @throws FormateurNotFoundException formateur not found 
     * @throws LienFormateurFormationException lien entre formateur et formation non existant 
     */
    boolean ajouterFormateurDansFormation(String code, int formateurkey) throws FormateurNotFoundException, LienFormateurFormationException;
    
    /**
     * supprimer un formateur de la formation
     * @param code code 
     * @param formateurkey formateur key
     * @return true si formateur supprimé de la formation
     * @throws FormateurNotFoundException formateur non trouvé
     * @throws LienFormateurFormationException  lien formateur formation non trouvé
     */
    boolean supprimerFormateurDeFormation(String code, int formateurkey) throws FormateurNotFoundException, LienFormateurFormationException;
    
    /**
     * ajouter salle dans formation
     * @param code code
     * @param sallekey salle key
     * @return true si salle ajouter à la formation
     * @throws FormateurNotFoundException formateur non trouvé
     * @throws LienFormateurFormationException lien formateur formation non trouvé
     */
    boolean ajouterSalleDansFormation(String code, int sallekey) throws FormateurNotFoundException, LienFormateurFormationException;
    
    /**
     * Supprimer salle d'une formation
     * @param code code
     * @param sallekey key salle
     * @return true si salle bien supprimé de la formation
     * @throws FormateurNotFoundException formateur non trouvé
     * @throws LienFormateurFormationException lien formateur formation non trouvé
     */
    boolean supprimerSalleDeFormation(String code, int sallekey) throws FormateurNotFoundException, LienFormateurFormationException;
}
