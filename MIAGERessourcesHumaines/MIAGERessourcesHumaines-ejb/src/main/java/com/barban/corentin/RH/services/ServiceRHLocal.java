 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.services;

import DTO.FormateurDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author julie
 */
@Local
public interface ServiceRHLocal {
    
    /**
     * vérifier l'existance du formateur
     * @param idFormateur id formateur 
     * @return true si le formateur existe sinon false 
     */
    boolean verifierExistenceFormateur(Integer idFormateur);

    List<FormateurDTO> listerFormateurs();
    
}
