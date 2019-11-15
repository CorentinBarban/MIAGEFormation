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
public class LienFormateurFormationException extends Exception {
    
    public LienFormateurFormationException() {
        System.out.println("Erreur : Ce formateur est déjà compétent pour cette formation.");
    }
}
