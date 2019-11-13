/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.entities;

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
@Table(name = "DEMANDEDEFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demandedeformation.findAll", query = "SELECT d FROM Demandedeformation d")
    , @NamedQuery(name = "Demandedeformation.findByIddemandeformation", query = "SELECT d FROM Demandedeformation d WHERE d.iddemandeformation = :iddemandeformation")
    , @NamedQuery(name = "Demandedeformation.findByNomclient", query = "SELECT d FROM Demandedeformation d WHERE d.nomclient = :nomclient")
    , @NamedQuery(name = "Demandedeformation.findByDatededemande", query = "SELECT d FROM Demandedeformation d WHERE d.datededemande = :datededemande")
    , @NamedQuery(name = "Demandedeformation.findByCodeformation", query = "SELECT d FROM Demandedeformation d WHERE d.codeformation = :codeformation")
    , @NamedQuery(name = "Demandedeformation.findByIntituleformation", query = "SELECT d FROM Demandedeformation d WHERE d.intituleformation = :intituleformation")
    , @NamedQuery(name = "Demandedeformation.findByCodeclient", query = "SELECT d FROM Demandedeformation d WHERE d.codeclient = :codeclient")})
public class Demandedeformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDEMANDEFORMATION")
    private Integer iddemandeformation;
    @Size(max = 50)
    @Column(name = "NOMCLIENT")
    private String nomclient;
    @Column(name = "DATEDEDEMANDE")
    @Temporal(TemporalType.DATE)
    private Date datededemande;
    @Size(max = 255)
    @Column(name = "CODEFORMATION")
    private String codeformation;
    @Size(max = 255)
    @Column(name = "INTITULEFORMATION")
    private String intituleformation;
    @Column(name = "CODECLIENT")
    private Integer codeclient;

    public Demandedeformation() {
    }

    public Demandedeformation(Integer iddemandeformation) {
        this.iddemandeformation = iddemandeformation;
    }

    public Integer getIddemandeformation() {
        return iddemandeformation;
    }

    public void setIddemandeformation(Integer iddemandeformation) {
        this.iddemandeformation = iddemandeformation;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public Date getDatededemande() {
        return datededemande;
    }

    public void setDatededemande(Date datededemande) {
        this.datededemande = datededemande;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddemandeformation != null ? iddemandeformation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demandedeformation)) {
            return false;
        }
        Demandedeformation other = (Demandedeformation) object;
        if ((this.iddemandeformation == null && other.iddemandeformation != null) || (this.iddemandeformation != null && !this.iddemandeformation.equals(other.iddemandeformation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.commercial.entities.Demandedeformation[ iddemandeformation=" + iddemandeformation + " ]";
    }
    
}
