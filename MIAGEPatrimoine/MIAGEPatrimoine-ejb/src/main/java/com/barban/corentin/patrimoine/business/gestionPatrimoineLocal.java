/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.business;

import DTO.SalleDTO;
import Exceptions.SalleNotFoundException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface gestionPatrimoineLocal {

    void editerStatutSalle(Integer idSalle, String statut, Date date) throws SalleNotFoundException;

    List<SalleDTO> listerSalleDisponible(List<SalleDTO> listSallesDemandees, Date date);
    
}
