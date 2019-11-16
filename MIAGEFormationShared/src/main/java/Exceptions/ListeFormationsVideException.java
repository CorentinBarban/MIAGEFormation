/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author maths
 */
public class ListeFormationsVideException extends Exception {
    
    public ListeFormationsVideException() {
        System.out.println("La liste de formations est vide.");
    }
    
}
