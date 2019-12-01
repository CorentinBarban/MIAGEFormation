/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Corentin
 */
public class FormateursDTO implements Serializable{
    
    private HashMap<FormateurDTO,List<Date>> listeFormateur;

    public FormateursDTO() {
    }

    public HashMap<FormateurDTO, List<Date>> getListeFormateur() {
        return listeFormateur;
    }

    public void setListeFormateur(HashMap<FormateurDTO, List<Date>> listeFormateur) {
        this.listeFormateur = listeFormateur;
    }

    @Override
    public String toString() {
        return "FormateursDTO{" + "listeFormateur=" + listeFormateur + '}';
    }
    
}
