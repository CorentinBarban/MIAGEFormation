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

    public DemandeFormationDTO() {

    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    
    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public List<FormateurDTO> getListFormateursPressentis() {
        return listFormateursPressentis;
    }

    public void setListFormateursPressentis(List<FormateurDTO> listFormateursPressentis) {
        this.listFormateursPressentis = listFormateursPressentis;
    }

    public List<SalleDTO> getListSallesPressenties() {
        return listSallesPressenties;
    }

    public void setListSallesPressenties(List<SalleDTO> listSallesPressenties) {
        this.listSallesPressenties = listSallesPressenties;
    }

    @Override
    public String toString() {
        return "DemandeFormationDTO{" + "codeFormation=" + codeFormation + ", codeClient=" + codeClient + ", nomClient=" + nomClient + ", intitule=" + intitule + ", nbPersonnes=" + nbPersonnes + ", listFormateursPressentis=" + listFormateursPressentis + ", listSallesPressenties=" + listSallesPressenties + '}';
    }

    
 
}
