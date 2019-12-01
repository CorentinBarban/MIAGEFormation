/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "STOCKAGEDEMANDEFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stockagedemandeformation.findAll", query = "SELECT s FROM Stockagedemandeformation s")
    , @NamedQuery(name = "Stockagedemandeformation.findByIddemandeformation", query = "SELECT s FROM Stockagedemandeformation s WHERE s.iddemandeformation = :iddemandeformation")
    , @NamedQuery(name = "Stockagedemandeformation.findByDatedemandeformation", query = "SELECT s FROM Stockagedemandeformation s WHERE s.datedemandeformation = :datedemandeformation")
    , @NamedQuery(name = "Stockagedemandeformation.findByCodeformation", query = "SELECT s FROM Stockagedemandeformation s WHERE s.codeformation = :codeformation")
    , @NamedQuery(name = "Stockagedemandeformation.findByIntituleformation", query = "SELECT s FROM Stockagedemandeformation s WHERE s.intituleformation = :intituleformation")
    , @NamedQuery(name = "Stockagedemandeformation.findByCodeclient", query = "SELECT s FROM Stockagedemandeformation s WHERE s.codeclient = :codeclient")})
public class Stockagedemandeformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDEMANDEFORMATION")
    private Integer iddemandeformation;
    @Column(name = "DATEDEMANDEFORMATION")
    @Temporal(TemporalType.DATE)
    private Date datedemandeformation;
    @Size(max = 255)
    @Column(name = "CODEFORMATION")
    private String codeformation;
    @Size(max = 255)
    @Column(name = "INTITULEFORMATION")
    private String intituleformation;
    @Column(name = "CODECLIENT")
    private Integer codeclient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockagedemandeformation")
    private Collection<Formationcompose> formationcomposeCollection;

    public Stockagedemandeformation() {
    }

    public Stockagedemandeformation(Integer iddemandeformation) {
        this.iddemandeformation = iddemandeformation;
    }

    public Integer getIddemandeformation() {
        return iddemandeformation;
    }

    public void setIddemandeformation(Integer iddemandeformation) {
        this.iddemandeformation = iddemandeformation;
    }

    public Date getDatedemandeformation() {
        return datedemandeformation;
    }

    public void setDatedemandeformation(Date datedemandeformation) {
        this.datedemandeformation = datedemandeformation;
    }

    public String getCodeformation() {
        return codeformation;
    }

    public void setCodeformation(String codeformation) {
        this.codeformation = codeformation;
    }

    public String getIntituleformation() {
        return intituleformation;
    }

    public void setIntituleformation(String intituleformation) {
        this.intituleformation = intituleformation;
    }

    public Integer getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(Integer codeclient) {
        this.codeclient = codeclient;
    }

    @XmlTransient
    public Collection<Formationcompose> getFormationcomposeCollection() {
        return formationcomposeCollection;
    }

    public void setFormationcomposeCollection(Collection<Formationcompose> formationcomposeCollection) {
        this.formationcomposeCollection = formationcomposeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddemandeformation != null ? iddemandeformation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stockagedemandeformation)) {
            return false;
        }
        Stockagedemandeformation other = (Stockagedemandeformation) object;
        if ((this.iddemandeformation == null && other.iddemandeformation != null) || (this.iddemandeformation != null && !this.iddemandeformation.equals(other.iddemandeformation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.formation.entities.Stockagedemandeformation[ iddemandeformation=" + iddemandeformation + " ]";
    }
    
}
