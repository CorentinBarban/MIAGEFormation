/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.repositories;

import com.barban.corentin.technicoCommercial.entities.Formateurcompetent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface FormateurcompetentFacadeLocal {

    Formateurcompetent create(Formateurcompetent formateurcompetent);

    void edit(Formateurcompetent formateurcompetent);

    void remove(Formateurcompetent formateurcompetent);

    Formateurcompetent find(Object id);

    List<Formateurcompetent> findAll();

    List<Formateurcompetent> findRange(int[] range);

    int count();
    
}
