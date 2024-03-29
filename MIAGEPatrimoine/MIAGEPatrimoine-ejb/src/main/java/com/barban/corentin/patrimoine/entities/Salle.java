/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "SALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s")
    , @NamedQuery(name = "Salle.findByIdsalle", query = "SELECT s FROM Salle s WHERE s.idsalle = :idsalle")
    , @NamedQuery(name = "Salle.findByNom", query = "SELECT s FROM Salle s WHERE s.nom = :nom")})
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSALLE")
    private Integer idsalle;
    @Size(max = 50)
    @Column(name = "NOM")
    private String nom;
    @JoinTable(name = "SALLE_EQUIPEMENT", joinColumns = {
        @JoinColumn(name = "SALLEKEY", referencedColumnName = "IDSALLE")}, inverseJoinColumns = {
        @JoinColumn(name = "EQUIPEMENTKEY", referencedColumnName = "IDEQUIPEMENT")})
    @ManyToMany
    private Collection<Equipement> equipementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salle")
    private Collection<CalendrierSalle> calendrierSalleCollection;

    public Salle() {
    }

    /** 
     * constructeur de la salle à partir de son id
     * @param idsalle id de la salle
     */
    public Salle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    /**
     * get id salle
     * @return id de la salle
     */
    public Integer getIdsalle() {
        return idsalle;
    }

    /**
     * set id salle
     * @param idsalle id de la salle
     */
    public void setIdsalle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    /**
     * get nom 
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * set nom
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get les équipements de la salle 
     * @return les équipements de la salle 
     */
    @XmlTransient
    public Collection<Equipement> getEquipementCollection() {
        return equipementCollection;
    }

    /**
     * set les équipements de la salle 
     * @param equipementCollection les équipements de la salle 
     */
    public void setEquipementCollection(Collection<Equipement> equipementCollection) {
        this.equipementCollection = equipementCollection;
    }

    /**
     * get les calendriers de la salle
     * @return les calendriers de la salle
     */
    @XmlTransient
    public Collection<CalendrierSalle> getCalendrierSalleCollection() {
        return calendrierSalleCollection;
    }

    /**
     * set les calendriers de la salle
     * @param calendrierSalleCollection les calendriers de la salle
     */
    public void setCalendrierSalleCollection(Collection<CalendrierSalle> calendrierSalleCollection) {
        this.calendrierSalleCollection = calendrierSalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsalle != null ? idsalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        if ((this.idsalle == null && other.idsalle != null) || (this.idsalle != null && !this.idsalle.equals(other.idsalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.patrimoine.entities.Salle[ idsalle=" + idsalle + " ]";
    }
    
}
