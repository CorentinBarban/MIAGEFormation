/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.repositories;

import com.barban.corentin.formation.entities.Stockagedemandeformation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corentin
 */
@Local
public interface StockagedemandeformationFacadeLocal {

    Stockagedemandeformation create(Stockagedemandeformation stockagedemandeformation);

    void edit(Stockagedemandeformation stockagedemandeformation);

    void remove(Stockagedemandeformation stockagedemandeformation);

    Stockagedemandeformation find(Object id);

    List<Stockagedemandeformation> findAll();

    List<Stockagedemandeformation> findRange(int[] range);

    int count();
    
}
