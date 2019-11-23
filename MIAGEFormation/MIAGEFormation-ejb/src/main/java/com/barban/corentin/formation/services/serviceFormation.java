/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.services;

import com.barban.corentin.formation.business.gestionFormationLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Corentin
 */
@Stateless
public class serviceFormation implements serviceFormationLocal {

    @EJB
    private gestionFormationLocal gestionFormation;

    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String demanderEtatFormation(Integer idFormation) {
        return this.gestionFormation.demanderEtatFormation(idFormation);
    }
}
