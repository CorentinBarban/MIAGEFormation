/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.barban.corentin.patrimoine.business.gestionPatrimoineLocal;

/**
 *
 * @author Corentin
 */
@Stateless
public class ServicePatrimoine implements ServicePatrimoineLocal {

    @EJB
    private gestionPatrimoineLocal gestionPatrimoine;
    
    @Override
    public boolean verifierExistenceSalle(Integer salleKey) {
        return this.gestionPatrimoine.validerExistenceSalle(salleKey);
    }

   
}
