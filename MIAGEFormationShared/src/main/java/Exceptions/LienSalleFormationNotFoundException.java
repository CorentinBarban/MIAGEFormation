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
public class LienSalleFormationNotFoundException extends Exception {
    
    public LienSalleFormationNotFoundException() {
        System.out.println("Erreur : Cette salle n'est pas considérée comme adéquate pour cette formation.");
    }
}
