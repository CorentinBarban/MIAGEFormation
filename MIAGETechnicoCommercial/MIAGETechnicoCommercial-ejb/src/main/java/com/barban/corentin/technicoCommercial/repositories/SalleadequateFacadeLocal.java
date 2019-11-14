/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.repositories;

import com.barban.corentin.technicoCommercial.entities.Salleadequate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface SalleadequateFacadeLocal {

    Salleadequate create(Salleadequate salleadequate);

    void edit(Salleadequate salleadequate);

    void remove(Salleadequate salleadequate);

    Salleadequate find(Object id);

    List<Salleadequate> findAll();

    List<Salleadequate> findRange(int[] range);

    int count();
    
}
