/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.repositories;

import com.barban.corentin.formation.entities.Formationcompose;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface FormationcomposeFacadeLocal {

    Formationcompose create(Formationcompose formationcompose);

    void edit(Formationcompose formationcompose);

    void remove(Formationcompose formationcompose);

    Formationcompose find(Object id);

    List<Formationcompose> findAll();

    List<Formationcompose> findRange(int[] range);

    int count();
    
}
