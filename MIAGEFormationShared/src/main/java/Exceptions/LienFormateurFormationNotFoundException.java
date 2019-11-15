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
public class LienFormateurFormationNotFoundException extends Exception {
    
    public LienFormateurFormationNotFoundException() {
        System.out.println("Erreur : Ce formateur n'est pas considéré comme compétent pour cette formation.");
    }
}
