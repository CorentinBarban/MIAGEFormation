/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Collection;

/**
 *
 * @author Corentin
 */
public class SalleDTO {
    
    private Integer idsalle;
    private String nom;
    private Collection<EquipementDTO> equipementCollection;
    private Collection<CalendrierSalleDTO> calendrierSalleCollection;

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

    public Collection<CalendrierSalleDTO> getCalendrierSalleCollection() {
        return calendrierSalleCollection;
    }

    public void setCalendrierSalleCollection(Collection<CalendrierSalleDTO> calendrierSalleCollection) {
        this.calendrierSalleCollection = calendrierSalleCollection;
    }

    @Override
    public String toString() {
        return "SalleDTO{" + "idsalle=" + idsalle + ", nom=" + nom + ", equipementCollection=" + equipementCollection + ", calendrierSalleCollection=" + calendrierSalleCollection + '}';
    }
    
    
}
