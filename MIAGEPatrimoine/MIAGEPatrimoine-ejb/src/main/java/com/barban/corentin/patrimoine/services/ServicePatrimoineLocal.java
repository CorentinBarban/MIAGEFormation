/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.services;

import DTO.SalleDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface ServicePatrimoineLocal {
    
     boolean verifierExistenceSalle(Integer salleKey);

    List<SalleDTO> listerSalles();
}
