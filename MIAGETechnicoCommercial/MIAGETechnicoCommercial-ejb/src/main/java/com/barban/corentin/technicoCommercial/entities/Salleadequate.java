/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "SALLEADEQUATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salleadequate.findAll", query = "SELECT s FROM Salleadequate s")
    , @NamedQuery(name = "Salleadequate.findByIdsalleadequat", query = "SELECT s FROM Salleadequate s WHERE s.idsalleadequat = :idsalleadequat")
    , @NamedQuery(name = "Salleadequate.findBySallekey", query = "SELECT s FROM Salleadequate s WHERE s.sallekey = :sallekey")})
public class Salleadequate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSALLEADEQUAT")
    private Integer idsalleadequat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALLEKEY")
    private int sallekey;
    @JoinTable(name = "FORMATIONCATALOGUE_SALLEADEQUATE", joinColumns = {
        @JoinColumn(name = "SALLEADEQUATEKEY", referencedColumnName = "IDSALLEADEQUAT")}, inverseJoinColumns = {
        @JoinColumn(name = "FORMATIONCATALOGUEKEY", referencedColumnName = "IDFORMATIONCATALOGUE")})
    @ManyToMany
    private Collection<Formationcatalogue> formationcatalogueCollection;

    public Salleadequate() {
    }

    public Salleadequate(Integer idsalleadequat) {
        this.idsalleadequat = idsalleadequat;
    }

    public Salleadequate(Integer idsalleadequat, int sallekey) {
        this.idsalleadequat = idsalleadequat;
        this.sallekey = sallekey;
    }

    public Integer getIdsalleadequat() {
        return idsalleadequat;
    }

    public void setIdsalleadequat(Integer idsalleadequat) {
        this.idsalleadequat = idsalleadequat;
    }

    public int getSallekey() {
        return sallekey;
    }

    public void setSallekey(int sallekey) {
        this.sallekey = sallekey;
    }

    @XmlTransient
    public Collection<Formationcatalogue> getFormationcatalogueCollection() {
        return formationcatalogueCollection;
    }

    public void setFormationcatalogueCollection(Collection<Formationcatalogue> formationcatalogueCollection) {
        this.formationcatalogueCollection = formationcatalogueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsalleadequat != null ? idsalleadequat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salleadequate)) {
            return false;
        }
        Salleadequate other = (Salleadequate) object;
        if ((this.idsalleadequat == null && other.idsalleadequat != null) || (this.idsalleadequat != null && !this.idsalleadequat.equals(other.idsalleadequat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.entities.Salleadequate[ idsalleadequat=" + idsalleadequat + " ]";
    }
    
}
