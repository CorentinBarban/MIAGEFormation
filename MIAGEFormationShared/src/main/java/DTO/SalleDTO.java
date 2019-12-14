/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Corentin
 */
public class SalleDTO implements Serializable{
    
    private Integer idsalle;
    private String nom;
    private Collection<EquipementDTO> equipementCollection;
    private Date date;
    private String statut;

    /**
     * get id de la salle 
     * @return id de la salle 
     */
    public Integer getIdsalle() {
        return idsalle;
    }

    /**
     * set id de la salle 
     * @param idsalle id de la salle 
     */
    public void setIdsalle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    /**
     * get nom de la salle 
     * @return nom de la salle 
     */
    public String getNom() {
        return nom;
    }

    /**
     * set nom de la salle 
     * @param nom nom de la salle 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get les équipement de la salle 
     * @return les équipement de la salle 
     */
    public Collection<EquipementDTO> getEquipementCollection() {
        return equipementCollection;
    }

    /**
     * set les équiment de la salle 
     * @param equipementCollection les équipement de la salle 
     */
    public void setEquipementCollection(Collection<EquipementDTO> equipementCollection) {
        this.equipementCollection = equipementCollection;
    }

    /**
     * get date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * set date
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * get statut de la salle 
     * @return statut de la salle 
     */
    public String getStatut() {
        return statut;
    }

    /**
     * set statut de la salle 
     * @param statut statut de la salle 
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "SalleDTO{" + "idsalle=" + idsalle + ", nom=" + nom + ", equipementCollection=" + equipementCollection + ", date=" + date + ", statut=" + statut + '}';
    }

    
    
}
