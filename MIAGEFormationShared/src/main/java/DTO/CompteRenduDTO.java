/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author maths
 */
public class CompteRenduDTO {
    
    String intituleFormation;
    Date dateFormation;
    String nomclient;
    String type;
    int nbpersonne;

    public String getIntituleFormation() {
        return intituleFormation;
    }

    public void setIntituleFormation(String intituleFormation) {
        this.intituleFormation = intituleFormation;
    }

    public Date getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(Date dateFormation) {
        this.dateFormation = dateFormation;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(int nbpersonne) {
        this.nbpersonne = nbpersonne;
    }
    
    

}
