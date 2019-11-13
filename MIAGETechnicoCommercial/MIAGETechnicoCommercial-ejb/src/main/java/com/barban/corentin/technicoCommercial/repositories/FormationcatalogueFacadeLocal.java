/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.repositories;

import com.barban.corentin.technicoCommercial.entities.Formationcatalogue;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface FormationcatalogueFacadeLocal {

    void create(Formationcatalogue formationcatalogue);

    void edit(Formationcatalogue formationcatalogue);

    void remove(Formationcatalogue formationcatalogue);

    Formationcatalogue find(Object id);

    List<Formationcatalogue> findAll();

    List<Formationcatalogue> findRange(int[] range);

    int count();
    
}
