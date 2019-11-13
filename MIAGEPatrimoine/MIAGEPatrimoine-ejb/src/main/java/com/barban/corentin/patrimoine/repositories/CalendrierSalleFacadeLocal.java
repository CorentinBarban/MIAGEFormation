/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.repositories;

import com.barban.corentin.patrimoine.entities.CalendrierSalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface CalendrierSalleFacadeLocal {

    void create(CalendrierSalle calendrierSalle);

    void edit(CalendrierSalle calendrierSalle);

    void remove(CalendrierSalle calendrierSalle);

    CalendrierSalle find(Object id);

    List<CalendrierSalle> findAll();

    List<CalendrierSalle> findRange(int[] range);

    int count();
    
}
