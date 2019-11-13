/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.repositories;

import com.barban.corentin.RH.entities.Formateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface FormateurFacadeLocal {

    Formateur create(Formateur formateur);

    void edit(Formateur formateur);

    void remove(Formateur formateur);

    Formateur find(Object id);

    List<Formateur> findAll();

    List<Formateur> findRange(int[] range);

    int count();
    
}
