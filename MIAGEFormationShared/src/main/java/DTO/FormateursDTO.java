/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Corentin
 */
public class FormateursDTO implements Serializable{
    
    private List<FormateurDTO> listeFormateur;

    public FormateursDTO() {
    }

    public List<FormateurDTO> getListeFormateur() {
        return listeFormateur;
    }

    public void setListeFormateur(List<FormateurDTO> listeFormateur) {
        this.listeFormateur = listeFormateur;
    }

    @Override
    public String toString() {
        return "FormateursDTO{" + "listeFormateur=" + listeFormateur + '}';
    }
    
    
    
}
