/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.services;

import DTO.SalleDTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.barban.corentin.patrimoine.business.gestionPatrimoineLocal;
import java.util.List;

/**
 *
 * @author Corentin
 */
@Stateless
public class ServicePatrimoine implements ServicePatrimoineLocal {

    @EJB
    private gestionPatrimoineLocal gestionPatrimoine;
    
    /**
     * Verifier l'existence d'une salle
     * @param salleKey Identifiant d'une salle
     * @return un boolean 
     */
    @Override
    public boolean verifierExistenceSalle(Integer salleKey) {
        return this.gestionPatrimoine.validerExistenceSalle(salleKey);
    }

    @Override
    public List<SalleDTO> listerSalles() {
        return this.gestionPatrimoine.listerSalles();
    }

   
}
