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
public class LienSalleFormationException extends Exception {
    
    public LienSalleFormationException() {
        System.out.println("Erreur : Cette salle est déjà adéquate pour cette formation.");
    }
}
