/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.repositories;

import com.barban.corentin.RH.entities.CalendrierFormateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface CalendrierFormateurFacadeLocal {

    CalendrierFormateur create(CalendrierFormateur calendrierFormateur);

    void edit(CalendrierFormateur calendrierFormateur);

    void remove(CalendrierFormateur calendrierFormateur);

    CalendrierFormateur find(Object id);

    List<CalendrierFormateur> findAll();

    List<CalendrierFormateur> findRange(int[] range);

    int count();
    
}
