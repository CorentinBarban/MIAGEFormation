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
 * @author julie
 */
public class DemandeFormationDTO implements Serializable {

    private String codeFormation;
    private int codeClient;
    private String nomClient;
    private String intitule;
    private int nbPersonnes;
    private List<FormateurDTO> listFormateursPressentis;
    private List<SalleDTO> listSallesPressenties;
    private int capaciteMax;
    private int capaciteMin;
    private String typeFormation;
    
    public DemandeFormationDTO() {

    }

    /**
     * get nombre de personne 
     * @return nombre de personne
     */
    public int getNbPersonnes() {
        return nbPersonnes;
    }

    /**
     * set nombre de personne 
     * @param nbPersonnes  nombre de personne
     */
    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    /**
     * get le nom du client
     * @return nom du client 
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * set le nom du client
     * @param nomClient nom du client
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     * get l'intitule 
     * @return intitule de la formation 
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * set l'intitule
     * @param intitule intitule de la formation 
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * get code de la formation 
     * @return code de la formation 
     */
    public String getCodeFormation() {
        return codeFormation;
    }

    /**
     * set code de la formation 
     * @param codeFormation code de la formation 
     */
    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    /**
     * get code du client 
     * @return code du client 
     */
    public int getCodeClient() {
        return codeClient;
    }

    /**
     * set code du client 
     * @param codeClient code du client 
     */
    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    /**
     * get la liste des formateurs préssentis de la formation 
     * @return  la liste des formateurs préssentis de la formation 
     */
    public List<FormateurDTO> getListFormateursPressentis() {
        return listFormateursPressentis;
    }

    /**
     * set la liste des formateurs pressentis
     * @param listFormateursPressentis la liste des formateurs pressentis
     */
    public void setListFormateursPressentis(List<FormateurDTO> listFormateursPressentis) {
        this.listFormateursPressentis = listFormateursPressentis;
    }

    /**
     * get la liste des salles pressenties
     * @return la liste des salles pressenties
     */
    public List<SalleDTO> getListSallesPressenties() {
        return listSallesPressenties;
    }

    /**
     * set la liste des salles pressenties
     * @param listSallesPressenties la liste des salles pressenties
     */
    public void setListSallesPressenties(List<SalleDTO> listSallesPressenties) {
        this.listSallesPressenties = listSallesPressenties;
    }

    /**
     *get la capacite maximum
     * @return la capacite maximum
     */
    public int getCapaciteMax() {
        return capaciteMax;
    }

    /**
     * set la capacite maximum
     * @param capaciteMax la capacite maximum
     */
    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    /**
     * get la capacite minimum
     * @return la capacite minimum
     */
    public int getCapaciteMin() {
        return capaciteMin;
    }

    /**
     * set la capacite minimum
     * @param capaciteMin la capacite minimum
     */
    public void setCapaciteMin(int capaciteMin) {
        this.capaciteMin = capaciteMin;
    }

    /**
     * get type de la formation 
     * @return type de la formation 
     */
    public String getTypeFormation() {
        return typeFormation;
    }

    /**
     * set type de la formation 
     * @param typeFormation type de la formation 
     */
    public void setTypeFormation(String typeFormation) {
        this.typeFormation = typeFormation;
    }

    @Override
    public String toString() {
        return "DemandeFormationDTO{" + "codeFormation=" + codeFormation + ", codeClient=" + codeClient + ", nomClient=" + nomClient + ", intitule=" + intitule + ", nbPersonnes=" + nbPersonnes + ", listFormateursPressentis=" + listFormateursPressentis + ", listSallesPressenties=" + listSallesPressenties + ", capaciteMax=" + capaciteMax + ", capaciteMin=" + capaciteMin + '}';
    }

       
 
}
