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
public class EquipementDTO {
    
    private Integer idequipement;
    private String nomequipement;
    private Collection<SalleDTO> salleCollection;

    public Integer getIdequipement() {
        return idequipement;
    }

    public void setIdequipement(Integer idequipement) {
        this.idequipement = idequipement;
    }

    public String getNomequipement() {
        return nomequipement;
    }

    public void setNomequipement(String nomequipement) {
        this.nomequipement = nomequipement;
    }

    public Collection<SalleDTO> getSalleCollection() {
        return salleCollection;
    }

    public void setSalleCollection(Collection<SalleDTO> salleCollection) {
        this.salleCollection = salleCollection;
    }

    @Override
    public String toString() {
        return "EquipementDTO{" + "idequipement=" + idequipement + ", nomequipement=" + nomequipement + ", salleCollection=" + salleCollection + '}';
    }
    
}
