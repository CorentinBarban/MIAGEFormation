/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maths
 */
public class CompteRenduDTO implements Serializable{
    
    String intituleFormation;
    Date dateCompteRendu;
    List<String> nomclient;
    String type;
    int nbpersonne;

    public CompteRenduDTO() {
    }

    public CompteRenduDTO(String intituleFormation, Date dateFormation, List<String> nomclient, String type, int nbpersonne) {
        this.intituleFormation = intituleFormation;
        this.dateCompteRendu = dateFormation;
        this.nomclient = nomclient;
        this.type = type;
        this.nbpersonne = nbpersonne;
    }

    public String getIntituleFormation() {
        return intituleFormation;
    }

    public void setIntituleFormation(String intituleFormation) {
        this.intituleFormation = intituleFormation;
    }

    public Date getDateFormation() {
        return dateCompteRendu;
    }

    public void setDateFormation(Date dateFormation) {
        this.dateCompteRendu = dateFormation;
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

    public Date getDateCompteRendu() {
        return dateCompteRendu;
    }

    public void setDateCompteRendu(Date dateCompteRendu) {
        this.dateCompteRendu = dateCompteRendu;
    }

    public List<String> getNomclient() {
        return nomclient;
    }

    public void setNomclient(List<String> nomclient) {
        this.nomclient = nomclient;
    }

    @Override
    public String toString() {
        return "CompteRenduDTO{" + "intituleFormation=" + intituleFormation + ", dateCompteRendu=" + dateCompteRendu + ", nomclient=" + nomclient + ", type=" + type + ", nbpersonne=" + nbpersonne + '}';
    }
      
    

}
