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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "FORMATIONCATALOGUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formationcatalogue.findAll", query = "SELECT f FROM Formationcatalogue f")
    , @NamedQuery(name = "Formationcatalogue.findByIdformationcatalogue", query = "SELECT f FROM Formationcatalogue f WHERE f.idformationcatalogue = :idformationcatalogue")
    , @NamedQuery(name = "Formationcatalogue.findByCode", query = "SELECT f FROM Formationcatalogue f WHERE f.code = :code")
    , @NamedQuery(name = "Formationcatalogue.findByIntitule", query = "SELECT f FROM Formationcatalogue f WHERE f.intitule = :intitule")
    , @NamedQuery(name = "Formationcatalogue.findByNiveau", query = "SELECT f FROM Formationcatalogue f WHERE f.niveau = :niveau")
    , @NamedQuery(name = "Formationcatalogue.findByTypeduree", query = "SELECT f FROM Formationcatalogue f WHERE f.typeduree = :typeduree")
    , @NamedQuery(name = "Formationcatalogue.findByCapacitemin", query = "SELECT f FROM Formationcatalogue f WHERE f.capacitemin = :capacitemin")
    , @NamedQuery(name = "Formationcatalogue.findByCapacitemax", query = "SELECT f FROM Formationcatalogue f WHERE f.capacitemax = :capacitemax")
    , @NamedQuery(name = "Formationcatalogue.findByTarifforfaitaire", query = "SELECT f FROM Formationcatalogue f WHERE f.tarifforfaitaire = :tarifforfaitaire")})
public class Formationcatalogue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFORMATIONCATALOGUE")
    private Integer idformationcatalogue;
    @Size(max = 10)
    @Column(name = "CODE")
    private String code;
    @Size(max = 50)
    @Column(name = "INTITULE")
    private String intitule;
    @Size(max = 255)
    @Column(name = "NIVEAU")
    private String niveau;
    @Size(max = 100)
    @Column(name = "TYPEDUREE")
    private String typeduree;
    @Column(name = "CAPACITEMIN")
    private Integer capacitemin;
    @Column(name = "CAPACITEMAX")
    private Integer capacitemax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIFFORFAITAIRE")
    private Double tarifforfaitaire;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "formationcatalogueCollection")
    private Collection<Salleadequate> salleadequateCollection;
    @JoinTable(name = "FORMATIONCATALOGUE_FORMATEUR", joinColumns = {
        @JoinColumn(name = "FORMATIONCATALOGUEKEY", referencedColumnName = "IDFORMATIONCATALOGUE")}, inverseJoinColumns = {
        @JoinColumn(name = "FORMATEURCOMPETENTKEY", referencedColumnName = "IDFORMATEURCOMPETENT")})
    @ManyToMany
    private Collection<Formateurcompetent> formateurcompetentCollection;

    public Formationcatalogue() {
    }
    
    public Formationcatalogue(String code, String intitule, String niveau, String typeduree, Integer capacitemin, Integer capacitemax, Double tarifforfaitaire) {
        this.code = code;
        this.intitule = intitule;
        this.niveau = niveau;
        this.typeduree = typeduree;
        this.capacitemin = capacitemin;
        this.capacitemax = capacitemax;
        this.tarifforfaitaire = tarifforfaitaire;
    }

    public Formationcatalogue(Integer idformationcatalogue) {
        this.idformationcatalogue = idformationcatalogue;
    }

    public Integer getIdformationcatalogue() {
        return idformationcatalogue;
    }

    public void setIdformationcatalogue(Integer idformationcatalogue) {
        this.idformationcatalogue = idformationcatalogue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTypeduree() {
        return typeduree;
    }

    public void setTypeduree(String typeduree) {
        this.typeduree = typeduree;
    }

    public Integer getCapacitemin() {
        return capacitemin;
    }

    public void setCapacitemin(Integer capacitemin) {
        this.capacitemin = capacitemin;
    }

    public Integer getCapacitemax() {
        return capacitemax;
    }

    public void setCapacitemax(Integer capacitemax) {
        this.capacitemax = capacitemax;
    }

    public Double getTarifforfaitaire() {
        return tarifforfaitaire;
    }

    public void setTarifforfaitaire(Double tarifforfaitaire) {
        this.tarifforfaitaire = tarifforfaitaire;
    }

    @XmlTransient
    public Collection<Salleadequate> getSalleadequateCollection() {
        return salleadequateCollection;
    }

    public void setSalleadequateCollection(Collection<Salleadequate> salleadequateCollection) {
        this.salleadequateCollection = salleadequateCollection;
    }

    @XmlTransient
    public Collection<Formateurcompetent> getFormateurcompetentCollection() {
        return formateurcompetentCollection;
    }

    public void setFormateurcompetentCollection(Collection<Formateurcompetent> formateurcompetentCollection) {
        this.formateurcompetentCollection = formateurcompetentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformationcatalogue != null ? idformationcatalogue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formationcatalogue)) {
            return false;
        }
        Formationcatalogue other = (Formationcatalogue) object;
        if ((this.idformationcatalogue == null && other.idformationcatalogue != null) || (this.idformationcatalogue != null && !this.idformationcatalogue.equals(other.idformationcatalogue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.entities.Formationcatalogue[ idformationcatalogue=" + idformationcatalogue + " ]";
    }
    
}
