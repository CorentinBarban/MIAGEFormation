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

    /**
     * get id de l'équipement
     * @return id de l'équipement
     */
    public Integer getIdequipement() {
        return idequipement;
    }

    /** 
     * set id de l'équipement
     * @param idequipement  id de l'équipement
     */
    public void setIdequipement(Integer idequipement) {
        this.idequipement = idequipement;
    }

    /**
     * get nom de l'équipement
     * @return  nom de l'équipement
     */
    public String getNomequipement() {
        return nomequipement;
    }

    /** 
     * set nom de l'équipement
     * @param nomequipement nom de l'équipement
     */
    public void setNomequipement(String nomequipement) {
        this.nomequipement = nomequipement;
    }

    /**
     * get les salles qui ont l'équipement
     * @return  les salles qui ont l'équipement
     */
    public Collection<SalleDTO> getSalleCollection() {
        return salleCollection;
    }

    /**
     * set  les salles qui ont l'équipement
     * @param salleCollection les salles qui ont l'équipement
     */
    public void setSalleCollection(Collection<SalleDTO> salleCollection) {
        this.salleCollection = salleCollection;
    }

    @Override
    public String toString() {
        return "EquipementDTO{" + "idequipement=" + idequipement + ", nomequipement=" + nomequipement + ", salleCollection=" + salleCollection + '}';
    }
    
    
    
}
