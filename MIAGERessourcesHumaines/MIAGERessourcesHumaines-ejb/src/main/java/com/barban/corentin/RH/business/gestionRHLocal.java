/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.business;

import Exceptions.FormateurNotFoundException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julie
 */
@Local
public interface gestionRHLocal {

    void editStatutFormateur(Integer idFormateur, String statut, Date date)throws FormateurNotFoundException;
    
  
}
