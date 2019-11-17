/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Corentin
 */
public class SalleDTO implements Serializable{
    
    private Integer idsalle;
    private String nom;
    private Collection<EquipementDTO> equipementCollection;

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

    @Override
    public String toString() {
        return "SalleDTO{" + "idsalle=" + idsalle + ", nom=" + nom + ", equipementCollection=" + equipementCollection + '}';
    }
   
}
