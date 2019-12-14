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

    /**
     * get intitule de la formation
     * @return intitule de la formation 
     */
    public String getIntituleFormation() {
        return intituleFormation;
    }

    /**
     * set intitule de la formation
     * @param intituleFormation intitule de la formation
     */
    public void setIntituleFormation(String intituleFormation) {
        this.intituleFormation = intituleFormation;
    }

    /**
     * get date du compte rendu 
     * @return date du CR
     */
    public Date getDateFormation() {
        return dateCompteRendu;
    }

    /**
     * set date du compte rendu
     * @param dateFormation de la formation
     */
    public void setDateFormation(Date dateFormation) {
        this.dateCompteRendu = dateFormation;
    }

    /**
     * return le type de compte rendu
     * @return le type de compte rendu 
     */
    public String getType() {
        return type;
    }

    /**
     * set le type de compte rendu 
     * @param type type de compte rendu 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get le nombre de personne 
     * @return nombre de personne 
     */
    public int getNbpersonne() {
        return nbpersonne;
    }

    /**
     * set le nombre de personne 
     * @param nbpersonne nombre de personne 
     */
    public void setNbpersonne(int nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    /**
     * get date du compte rendu
     * @return date du compte rendu 
     */
    public Date getDateCompteRendu() {
        return dateCompteRendu;
    }

    /**
     * set date du compte rendu
     * @param dateCompteRendu date du compte rendu 
     */
    public void setDateCompteRendu(Date dateCompteRendu) {
        this.dateCompteRendu = dateCompteRendu;
    }

    /**
     * get nom du client 
     * @return nom du client
     */
    public List<String> getNomclient() {
        return nomclient;
    }

    /**
     * set nom du client 
     * @param nomclient nom du client
     */
    public void setNomclient(List<String> nomclient) {
        this.nomclient = nomclient;
    }

    @Override
    public String toString() {
        return "CompteRenduDTO{" + "intituleFormation=" + intituleFormation + ", dateCompteRendu=" + dateCompteRendu + ", nomclient=" + nomclient + ", type=" + type + ", nbpersonne=" + nbpersonne + '}';
    }
      
    

}
