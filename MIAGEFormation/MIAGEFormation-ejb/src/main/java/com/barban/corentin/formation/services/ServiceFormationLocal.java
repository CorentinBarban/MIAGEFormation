/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.services;

import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface ServiceFormationLocal {
    
    /**
     * Demander l'état d'une formation
     * @param idFormation id de la formation
     * @return l'état d'une formation 
     */
    String demanderEtatFormation(Integer idFormation);
    
}
