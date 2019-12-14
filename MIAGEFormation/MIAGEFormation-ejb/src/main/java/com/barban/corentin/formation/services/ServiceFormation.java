/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.barban.corentin.formation.business.GestionFormationLocal;

/**
 *
 * @author Corentin
 */
@Stateless
public class ServiceFormation implements ServiceFormationLocal {

    @EJB
    private GestionFormationLocal gestionFormation;

    
    /**
     * Demander l'état d'une formation
     * @param idFormation id de la formation
     * @return l'état d'une formation
     */
    @Override
    public String demanderEtatFormation(Integer idFormation) {
        return this.gestionFormation.demanderEtatFormation(idFormation);
    }
}
