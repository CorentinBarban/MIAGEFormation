/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.repositories;

import com.barban.corentin.commercial.entities.Demandedeformation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface DemandedeformationFacadeLocal {

    Demandedeformation create(Demandedeformation demandedeformation);

    void edit(Demandedeformation demandedeformation);

    void remove(Demandedeformation demandedeformation);

    Demandedeformation find(Object id);

    List<Demandedeformation> findAll();

    List<Demandedeformation> findRange(int[] range);

    int count();
    
}
