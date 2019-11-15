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
public class FormationCatalogueException extends Exception {
    
    public FormationCatalogueException() {
        System.out.println("Erreur : La formation existe déjà dans le catalogue.");
    }
}
