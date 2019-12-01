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
@Table(name = "FORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formation.findAll", query = "SELECT f FROM Formation f")
    , @NamedQuery(name = "Formation.findByIdformation", query = "SELECT f FROM Formation f WHERE f.idformation = :idformation")
    , @NamedQuery(name = "Formation.findByKeysalle", query = "SELECT f FROM Formation f WHERE f.keysalle = :keysalle")
    , @NamedQuery(name = "Formation.findByKeyformateur", query = "SELECT f FROM Formation f WHERE f.keyformateur = :keyformateur")
    , @NamedQuery(name = "Formation.findByDateformation", query = "SELECT f FROM Formation f WHERE f.dateformation = :dateformation")
    , @NamedQuery(name = "Formation.findByStatut", query = "SELECT f FROM Formation f WHERE f.statut = :statut")})
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFORMATION")
    private Integer idformation;
    @Column(name = "KEYSALLE")
    private Integer keysalle;
    @Column(name = "KEYFORMATEUR")
    private Integer keyformateur;
    @Column(name = "DATEFORMATION")
    @Temporal(TemporalType.DATE)
    private Date dateformation;
    @Size(max = 100)
    @Column(name = "STATUT")
    private String statut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formation")
    private Collection<Formationcompose> formationcomposeCollection;

    public Formation() {
    }

    public Formation(Integer idformation) {
        this.idformation = idformation;
    }

    public Integer getIdformation() {
        return idformation;
    }

    public void setIdformation(Integer idformation) {
        this.idformation = idformation;
    }

    public Integer getKeysalle() {
        return keysalle;
    }

    public void setKeysalle(Integer keysalle) {
        this.keysalle = keysalle;
    }

    public Integer getKeyformateur() {
        return keyformateur;
    }

    public void setKeyformateur(Integer keyformateur) {
        this.keyformateur = keyformateur;
    }

    public Date getDateformation() {
        return dateformation;
    }

    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
        hash += (idformation != null ? idformation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.idformation == null && other.idformation != null) || (this.idformation != null && !this.idformation.equals(other.idformation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.formation.entities.Formation[ idformation=" + idformation + " ]";
    }
    
}
