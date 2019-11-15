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
public class FormateurNotFoundException extends Exception {
    
    public FormateurNotFoundException() {
        System.out.println("Erreur : Le formateur demandé n'existe pas.");
    }
}
