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
    String code;
    String nomclient;
    int nbpersonne;
    String statut;
    String niveau;
    String typeduree;
    Integer capacitemin;
    Integer capacitemax;
    Double tarifforfaitaire;
    Date dateformation;
    int keyformationcatalogue;
    int keyformateur;
    int keysalle;
    int keyStockagedemandeformation;
    
    
    /*public FormationDTO() {
    }

    public FormationDTO(Integer idformation) {
        this.idformation = idformation;
    }*/

    public FormationDTO(String intitule, String code, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) {
        this.intitule = intitule;
        this.code = code;
        this.niveau = niveau;
        this.typeduree = typeduree;
        this.capacitemin = capacitemin;
        this.capacitemax = capacitemax;
        this.tarifforfaitaire = tarifforfaitaire;
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

    public int getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(int nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTypeduree() {
        return typeduree;
    }

    public void setTypeduree(String typeduree) {
        this.typeduree = typeduree;
    }

    public int getCapacitemin() {
        return capacitemin;
    }

    public void setCapacitemin(int capacitemin) {
        this.capacitemin = capacitemin;
    }

    public int getCapacitemax() {
        return capacitemax;
    }

    public void setCapacitemax(int capacitemax) {
        this.capacitemax = capacitemax;
    }

    public double getTarifforfaitaire() {
        return tarifforfaitaire;
    }

    public void setTarifforfaitaire(double tarifforfaitaire) {
        this.tarifforfaitaire = tarifforfaitaire;
    }

    public Date getDateformation() {
        return dateformation;
    }

    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
    }

    public int getKeyformationcatalogue() {
        return keyformationcatalogue;
    }

    public void setKeyformationcatalogue(int keyformationcatalogue) {
        this.keyformationcatalogue = keyformationcatalogue;
    }

    public int getKeyformateur() {
        return keyformateur;
    }

    public void setKeyformateur(int keyformateur) {
        this.keyformateur = keyformateur;
    }

    public int getKeysalle() {
        return keysalle;
    }

    public void setKeysalle(int keysalle) {
        this.keysalle = keysalle;
    }

    public int getKeyStockagedemandeformation() {
        return keyStockagedemandeformation;
    }

    public void setKeyStockagedemandeformation(int keyStockagedemandeformation) {
        this.keyStockagedemandeformation = keyStockagedemandeformation;
    }

    @Override
    public String toString() {
        return "FormationDTO{" + "idformation=" + idformation + ", intitule=" + intitule + ", code=" + code + ", nomclient=" + nomclient + ", nbpersonne=" + nbpersonne + ", statut=" + statut + ", niveau=" + niveau + ", typeduree=" + typeduree + ", capacitemin=" + capacitemin + ", capacitemax=" + capacitemax + ", tarifforfaitaire=" + tarifforfaitaire + ", dateformation=" + dateformation + ", keyformationcatalogue=" + keyformationcatalogue + ", keyformateur=" + keyformateur + ", keysalle=" + keysalle + ", keyStockagedemandeformation=" + keyStockagedemandeformation + '}';
    }
   
    

}
