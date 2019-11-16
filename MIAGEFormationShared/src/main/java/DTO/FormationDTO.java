/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Corentin
 */
public class FormationDTO {
    
    Integer idformation;
    String intitule;
    String nomclient;
    int nbpersonne;
    String statut;
    Date dateformation;
    int keyformationcatalogue;
    int keyformateur;
    int keysalle;
    int keyStockagedemandeformation;
    
    
    public FormationDTO() {
    }

    public FormationDTO(Integer idformation) {
        this.idformation = idformation;
    }

    public Integer getIdformation() {
        return idformation;
    }

    public void setIdformation(Integer idformation) {
        this.idformation = idformation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public Integer getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(Integer nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateformation() {
        return dateformation;
    }

    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
    }

    public Integer getKeyformationcatalogue() {
        return keyformationcatalogue;
    }

    public void setKeyformationcatalogue(Integer keyformationcatalogue) {
        this.keyformationcatalogue = keyformationcatalogue;
    }

    public Integer getKeyformateur() {
        return keyformateur;
    }

    public void setKeyformateur(Integer keyformateur) {
        this.keyformateur = keyformateur;
    }

    public Integer getKeysalle() {
        return keysalle;
    }

    public void setKeysalle(Integer keysalle) {
        this.keysalle = keysalle;
    }

    public Integer getKeyStockagedemandeformation() {
        return keyStockagedemandeformation;
    }

    public void setKeyStockagedemandeformation(Integer keyStockagedemandeformation) {
        this.keyStockagedemandeformation = keyStockagedemandeformation;
    }

    @Override
    public String toString() {
        return "FormationDTO{" + "idformation=" + idformation + ", intitule=" + intitule + ", nomclient=" + nomclient + ", nbpersonne=" + nbpersonne + ", statut=" + statut + ", dateformation=" + dateformation + ", keyformationcatalogue=" + keyformationcatalogue + ", keyformateur=" + keyformateur + ", keysalle=" + keysalle + ", keyStockagedemandeformation=" + keyStockagedemandeformation + '}';
    }

    

}
