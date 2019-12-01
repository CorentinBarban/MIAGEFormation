/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.entities;

import java.io.Serializable;
import java.util.Collection;
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
    , @NamedQuery(name = "Formation.findByNomclient", query = "SELECT f FROM Formation f WHERE f.nomclient = :nomclient")
    , @NamedQuery(name = "Formation.findByCodeformationcatalogue", query = "SELECT f FROM Formation f WHERE f.codeformationcatalogue = :codeformationcatalogue")})
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFORMATION")
    private Integer idformation;
    @Size(max = 100)
    @Column(name = "NOMCLIENT")
    private String nomclient;
    @Size(max = 100)
    @Column(name = "CODEFORMATIONCATALOGUE")
    private String codeformationcatalogue;
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

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getCodeformationcatalogue() {
        return codeformationcatalogue;
    }

    public void setCodeformationcatalogue(String codeformationcatalogue) {
        this.codeformationcatalogue = codeformationcatalogue;
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
