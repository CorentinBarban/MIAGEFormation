/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.services;

import DTO.FormateurDTO;
import com.barban.corentin.RH.business.gestionRHLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author julie
 */
@Stateless
public class ServiceRH implements ServiceRHLocal {

    @EJB
    private gestionRHLocal gestionRH;
    
    
    @Override
    public boolean verifierExistenceFormateur(Integer idFormateur){
        return this.gestionRH.verifierExistenceFormateur(idFormateur);
    }

    @Override
    public List<FormateurDTO> listerFormateurs() {
        return this.gestionRH.listerFormateurs();
    }
    
    
}
