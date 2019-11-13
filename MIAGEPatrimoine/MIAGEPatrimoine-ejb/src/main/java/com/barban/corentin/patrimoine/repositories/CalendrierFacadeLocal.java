/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.repositories;

import com.barban.corentin.patrimoine.entities.Calendrier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface CalendrierFacadeLocal {

    Calendrier create(Calendrier calendrier);

    void edit(Calendrier calendrier);

    void remove(Calendrier calendrier);

    Calendrier find(Object id);

    List<Calendrier> findAll();

    List<Calendrier> findRange(int[] range);

    int count();
    
}
