/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "EQUIPEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipement.findAll", query = "SELECT e FROM Equipement e")
    , @NamedQuery(name = "Equipement.findByIdequipement", query = "SELECT e FROM Equipement e WHERE e.idequipement = :idequipement")
    , @NamedQuery(name = "Equipement.findByNomequipement", query = "SELECT e FROM Equipement e WHERE e.nomequipement = :nomequipement")})
public class Equipement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEQUIPEMENT")
    private Integer idequipement;
    @Size(max = 255)
    @Column(name = "NOMEQUIPEMENT")
    private String nomequipement;
    @ManyToMany(mappedBy = "equipementCollection")
    private Collection<Salle> salleCollection;

    public Equipement() {
    }

    /**
     * constructeur equipement à partir de son id
     * @param idequipement id equipement 
     */
    public Equipement(Integer idequipement) {
        this.idequipement = idequipement;
    }

    /**
     * get id équipement
     * @return id équipement
     */
    public Integer getIdequipement() {
        return idequipement;
    }

    /**
     * set id équipement
     * @param idequipement id équipement
     */
    public void setIdequipement(Integer idequipement) {
        this.idequipement = idequipement;
    }

    /**
     * get nom équipement
     * @return  nom équipement
     */
    public String getNomequipement() {
        return nomequipement;
    }

    /**
     * set nom équipement
     * @param nomequipement  nom équipement
     */
    public void setNomequipement(String nomequipement) {
        this.nomequipement = nomequipement;
    }

   
    @XmlTransient
    /**
     * get la collection de salle qui possède l'équipement
     */
    public Collection<Salle> getSalleCollection() {
        return salleCollection;
    }

    /**
     * set la collection de salle qui possède l'équipement
     * @param salleCollection collection de salle  
     */
    public void setSalleCollection(Collection<Salle> salleCollection) {
        this.salleCollection = salleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipement != null ? idequipement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipement)) {
            return false;
        }
        Equipement other = (Equipement) object;
        if ((this.idequipement == null && other.idequipement != null) || (this.idequipement != null && !this.idequipement.equals(other.idequipement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.patrimoine.entities.Equipement[ idequipement=" + idequipement + " ]";
    }
    
}
