/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.repositories;

import com.barban.corentin.patrimoine.entities.Salle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface SalleFacadeLocal {

    Salle create(Salle salle);

    void edit(Salle salle);

    void remove(Salle salle);

    Salle find(Object id);

    List<Salle> findAll();

    List<Salle> findRange(int[] range);

    int count();
    
}
