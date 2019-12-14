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
 * @author Corentin
 */
public class FormationDTO implements Serializable{
    
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
    List<String> listeClient;
    Date dateDemandeformation;
    
    
    public FormationDTO() {
    }


    public FormationDTO(String intitule, String code, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) {
        this.intitule = intitule;
        this.code = code;
        this.niveau = niveau;
        this.typeduree = typeduree;
        this.capacitemin = capacitemin;
        this.capacitemax = capacitemax;
        this.tarifforfaitaire = tarifforfaitaire;
    }

    /**
     * get id de la formation
     * @return id de la formation
     */
    public Integer getIdformation() {
        return idformation;
    }

    /**
     * set id de la formation
     * @param idformation id de la formation
     */
    public void setIdformation(Integer idformation) {
        this.idformation = idformation;
    }

    /**
     * get intitule de la formation
     * @return  intitule de la formation
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * set intitule de la formation
     * @param intitule  intitule de la formation
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * get nom client de la formation
     * @return nom client de la formation
     */
    public String getNomclient() {
        return nomclient;
    }

    /**
     * set nom client de la formation
     * @param nomclient nom client de la formation
     */
    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    /**
     * get nombre de personne 
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
     * get statut de la formation 
     * @return statut de la formation 
     */
    public String getStatut() {
        return statut;
    }

    /**
     * set statut de la formation
     * @param statut statut de la formation 
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * get niveau de la formation
     * @return niveau de la formation
     */
    public String getNiveau() {
        return niveau;
    }

    /**
     * set niveau de la formation
     * @param niveau niveau de la formation
     */
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    /**
     * get type duree de la formation
     * @return type duree de la formation
     */
    public String getTypeduree() {
        return typeduree;
    }

    /**
     * set type duree de la formation
     * @param typeduree type duree de la formation
     */
    public void setTypeduree(String typeduree) {
        this.typeduree = typeduree;
    }

    /**
     * get capacite minimum de la formation
     * @return capacite minimum de la formation
     */
    public int getCapacitemin() {
        return capacitemin;
    }

    /**
     * set capacite minimum de la formation 
     * @param capacitemin capacite minimum de la formation 
     */
    public void setCapacitemin(int capacitemin) {
        this.capacitemin = capacitemin;
    }

    /**
     * get capacite maximum de la formation
     * @return capacite maximum de la formation
     */
    public int getCapacitemax() {
        return capacitemax;
    }

    /**
     * set capacite maximum de la formation
     * @param capacitemax capacite maximum de la formation
     */
    public void setCapacitemax(int capacitemax) {
        this.capacitemax = capacitemax;
    }

    /**
     * get tarif forfaitaire de la formation
     * @return tarif forfaitaire de la formation
     */
    public double getTarifforfaitaire() {
        return tarifforfaitaire;
    }

    /**
     * set tarif forfaitaire 
     * @param tarifforfaitaire tarif forfaitaire de la formation
     */
    public void setTarifforfaitaire(double tarifforfaitaire) {
        this.tarifforfaitaire = tarifforfaitaire;
    }

    /**
     * get Date formation 
     * @return  Date formation 
     */
    public Date getDateformation() {
        return dateformation;
    }

    /**
     * set date formation 
     * @param dateformation  Date formation 
     */
    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
    }

    /**
     * get Key formation catalogue
     * @return Key formation catalogue
     */
    public int getKeyformationcatalogue() {
        return keyformationcatalogue;
    }

    /** 
     * set Key formation catalogue
     * @param keyformationcatalogue Key formation catalogue
     */
    public void setKeyformationcatalogue(int keyformationcatalogue) {
        this.keyformationcatalogue = keyformationcatalogue;
    }

    /**
     * get key formateur 
     * @return key formateur 
     */
    public int getKeyformateur() {
        return keyformateur;
    }

    /**
     * set key formateur 
     * @param keyformateur key formateur 
     */
    public void setKeyformateur(int keyformateur) {
        this.keyformateur = keyformateur;
    }

    /**
     * get key salle
     * @return key salle
     */
    public int getKeysalle() {
        return keysalle;
    }

    /** 
     * set key salle 
     * @param keysalle key salle 
     */
    public void setKeysalle(int keysalle) {
        this.keysalle = keysalle;
    }

    /**
     * get key stockage demande formation
     * @return key stockage demande formation
     */
    public int getKeyStockagedemandeformation() {
        return keyStockagedemandeformation;
    }

    /**
     * set key stockage demande formation
     * @param keyStockagedemandeformation key stockage demande formation
     */
    public void setKeyStockagedemandeformation(int keyStockagedemandeformation) {
        this.keyStockagedemandeformation = keyStockagedemandeformation;
    }

    /**
     * get liste client de la formation 
     * @return liste client de la formation 
     */
    public List<String> getListeClient() {
        return listeClient;
    }

    /**
     * set liste client de la formation 
     * @param listeClient liste client de la formation 
     */
    public void setListeClient(List<String> listeClient) {
        this.listeClient = listeClient;
    }

    /**
     * get code de la formation
     * @return code de la formation
     */
    public String getCode() {
        return code;
    }

    /**
     * set code de la formation
     * @param code code de la formation
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get date demande formaiton 
     * @return date demande formaiton 
     */
    public Date getDateDemandeformation() {
        return dateDemandeformation;
    }

    /**
     * set date demande formation 
     * @param dateDemandeformation date demande formaiton 
     */
    public void setDateDemandeformation(Date dateDemandeformation) {
        this.dateDemandeformation = dateDemandeformation;
    }

           
    @Override
    public String toString() {
        return "FormationDTO{" + "idformation=" + idformation + ", intitule=" + intitule + ", code=" + code + ", nomclient=" + nomclient + ", nbpersonne=" + nbpersonne + ", statut=" + statut + ", niveau=" + niveau + ", typeduree=" + typeduree + ", capacitemin=" + capacitemin + ", capacitemax=" + capacitemax + ", tarifforfaitaire=" + tarifforfaitaire + ", dateformation=" + dateformation + ", keyformationcatalogue=" + keyformationcatalogue + ", keyformateur=" + keyformateur + ", keysalle=" + keysalle + ", keyStockagedemandeformation=" + keyStockagedemandeformation + '}';
    }
   
    

}
