/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.business;

import java.util.Date;

/**
 *
 * @author maths
 */
public interface gestionCommercialeLocal {
    
    void recupererCatalogueFormations(); 
    
    void memoriserDemandeFormation(int code, int numClient, Date dateFor);
    
    void editerCompteRendus();
    
    void demanderStatutFormation(int idFormation);
    
    void validerExistenceFormation(int code);
}
