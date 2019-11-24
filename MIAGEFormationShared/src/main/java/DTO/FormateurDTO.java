/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author julie
 */
public class FormateurDTO implements Serializable{

    private int idFormateur;
    private String nom;
    private String prenom;
    private Collection<CompetenceDTO> competenceCollection;

    public int getIdFormateur() {
        return idFormateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }
    

    public Collection<CompetenceDTO> getCompetenceCollection() {
        return competenceCollection;
    }

    public void setCompetenceCollection(Collection<CompetenceDTO> competenceCollection) {
        this.competenceCollection = competenceCollection;
    }

    @Override
    public String toString() {
        return "FormateurDTO{" + "idFormateur=" + idFormateur + ", nom=" + nom + ", prénom=" + prenom + 
                ", competenceCollection=" + competenceCollection + '}';
    }
   

}
