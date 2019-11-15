/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author jdetr
 */
public class FormationCatalogueNotFoundException extends Exception {
    
    public FormationCatalogueNotFoundException() {
        System.out.println("Erreur : La formation demandée n'existe pas dans le catalogue.");
    }
}
