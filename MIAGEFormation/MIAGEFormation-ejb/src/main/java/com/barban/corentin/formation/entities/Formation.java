/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    , @NamedQuery(name = "Formation.findByIntitule", query = "SELECT f FROM Formation f WHERE f.intitule = :intitule")
    , @NamedQuery(name = "Formation.findByNomclient", query = "SELECT f FROM Formation f WHERE f.nomclient = :nomclient")
    , @NamedQuery(name = "Formation.findByNbpersonne", query = "SELECT f FROM Formation f WHERE f.nbpersonne = :nbpersonne")
    , @NamedQuery(name = "Formation.findByStatut", query = "SELECT f FROM Formation f WHERE f.statut = :statut")
    , @NamedQuery(name = "Formation.findByDateformation", query = "SELECT f FROM Formation f WHERE f.dateformation = :dateformation")
    , @NamedQuery(name = "Formation.findByKeyformationcatalogue", query = "SELECT f FROM Formation f WHERE f.keyformationcatalogue = :keyformationcatalogue")
    , @NamedQuery(name = "Formation.findByKeyformateur", query = "SELECT f FROM Formation f WHERE f.keyformateur = :keyformateur")
    , @NamedQuery(name = "Formation.findByKeysalle", query = "SELECT f FROM Formation f WHERE f.keysalle = :keysalle")
    , @NamedQuery(name = "Formation.findByKeydemandeformation", query = "SELECT f FROM Formation f WHERE f.keydemandeformation = :keydemandeformation")})
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFORMATION")
    private Integer idformation;
    @Size(max = 100)
    @Column(name = "INTITULE")
    private String intitule;
    @Size(max = 100)
    @Column(name = "NOMCLIENT")
    private String nomclient;
    @Column(name = "NBPERSONNE")
    private Integer nbpersonne;
    @Size(max = 100)
    @Column(name = "STATUT")
    private String statut;
    @Column(name = "DATEFORMATION")
    @Temporal(TemporalType.DATE)
    private Date dateformation;
    @Column(name = "KEYFORMATIONCATALOGUE")
    private Integer keyformationcatalogue;
    @Column(name = "KEYFORMATEUR")
    private Integer keyformateur;
    @Column(name = "KEYSALLE")
    private Integer keysalle;
    @Column(name = "KEYDEMANDEFORMATION")
    private Integer keydemandeformation;

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

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public Integer getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(Integer nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateformation() {
        return dateformation;
    }

    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
    }

    public Integer getKeyformationcatalogue() {
        return keyformationcatalogue;
    }

    public void setKeyformationcatalogue(Integer keyformationcatalogue) {
        this.keyformationcatalogue = keyformationcatalogue;
    }

    public Integer getKeyformateur() {
        return keyformateur;
    }

    public void setKeyformateur(Integer keyformateur) {
        this.keyformateur = keyformateur;
    }

    public Integer getKeysalle() {
        return keysalle;
    }

    public void setKeysalle(Integer keysalle) {
        this.keysalle = keysalle;
    }

    public Integer getKeydemandeformation() {
        return keydemandeformation;
    }

    public void setKeydemandeformation(Integer keydemandeformation) {
        this.keydemandeformation = keydemandeformation;
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
