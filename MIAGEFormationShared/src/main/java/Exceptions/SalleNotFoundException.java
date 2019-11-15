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
public class SalleNotFoundException extends Exception {
    
    public SalleNotFoundException() {
        System.out.println("Erreur : La salle demandée n'existe pas.");
    }
}
