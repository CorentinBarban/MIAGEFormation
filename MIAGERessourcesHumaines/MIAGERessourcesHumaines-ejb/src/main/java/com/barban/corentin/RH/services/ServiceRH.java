/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.services;

import com.barban.corentin.RH.business.gestionRHLocal;
import javax.ejb.Stateless;

/**
 *
 * @author julie
 */
@Stateless
public class ServiceRH implements ServiceRHLocal {
    
    private gestionRHLocal gestionRH;

    @Override
    public boolean verifierExistenceFormateur(Integer idFormateur){
        return this.gestionRH.verifierExistenceFormateur(idFormateur);
    }
}
