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

    public Integer getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<EquipementDTO> getEquipementCollection() {
        return equipementCollection;
    }

    public void setEquipementCollection(Collection<EquipementDTO> equipementCollection) {
        this.equipementCollection = equipementCollection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "SalleDTO{" + "idsalle=" + idsalle + ", nom=" + nom + ", equipementCollection=" + equipementCollection + ", date=" + date + ", statut=" + statut + '}';
    }

    
    
}
