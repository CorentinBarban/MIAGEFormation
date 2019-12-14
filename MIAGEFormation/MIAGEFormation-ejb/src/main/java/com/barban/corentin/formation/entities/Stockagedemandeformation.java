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
    , @NamedQuery(name = "Stockagedemandeformation.findByCodeformationcatalogue", query = "SELECT s FROM Stockagedemandeformation s WHERE s.codeformationcatalogue = :codeformationcatalogue")
    , @NamedQuery(name = "Stockagedemandeformation.findByIntituleformation", query = "SELECT s FROM Stockagedemandeformation s WHERE s.intituleformation = :intituleformation")
    , @NamedQuery(name = "Stockagedemandeformation.findByCodeclient", query = "SELECT s FROM Stockagedemandeformation s WHERE s.codeclient = :codeclient")
    , @NamedQuery(name = "Stockagedemandeformation.findByNbpersonne", query = "SELECT s FROM Stockagedemandeformation s WHERE s.nbpersonne = :nbpersonne")
    , @NamedQuery(name = "Stockagedemandeformation.findByNomclient", query = "SELECT s FROM Stockagedemandeformation s WHERE s.nomclient = :nomclient")})
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
    @Size(max = 100)
    @Column(name = "CODEFORMATIONCATALOGUE")
    private String codeformationcatalogue;
    @Size(max = 255)
    @Column(name = "INTITULEFORMATION")
    private String intituleformation;
    @Column(name = "CODECLIENT")
    private Integer codeclient;
    @Column(name = "NBPERSONNE")
    private Integer nbpersonne;
    @Size(max = 100)
    @Column(name = "NOMCLIENT")
    private String nomclient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockagedemandeformation")
    private Collection<Formationcompose> formationcomposeCollection;

    public Stockagedemandeformation() {
    }

    /**
     * Constructeur sockage demande formation à partir de l'id demande formation
     * @param iddemandeformation id demande formation 
     */
    public Stockagedemandeformation(Integer iddemandeformation) {
        this.iddemandeformation = iddemandeformation;
    }

    /**
     * get Id demande formation
     *
     * @return id demande formation
     */
    public Integer getIddemandeformation() {
        return iddemandeformation;
    }

    /**
     * Set id demande formation
     *
     * @param iddemandeformation id demande formation
     */
    public void setIddemandeformation(Integer iddemandeformation) {
        this.iddemandeformation = iddemandeformation;
    }

    /**
     * get date demande formation
     *
     * @return date demande formation
     */
    public Date getDatedemandeformation() {
        return datedemandeformation;
    }

    /**
     * set date demande formation
     *
     * @param datedemandeformation date demande formation
     */
    public void setDatedemandeformation(Date datedemandeformation) {
        this.datedemandeformation = datedemandeformation;
    }

    /**
     * get Code formation catalogue
     *
     * @return Code formation catalogue
     */
    public String getCodeformationcatalogue() {
        return codeformationcatalogue;
    }

    /**
     * set code formation catalogue
     *
     * @param codeformationcatalogue Code formation catalogue
     */
    public void setCodeformationcatalogue(String codeformationcatalogue) {
        this.codeformationcatalogue = codeformationcatalogue;
    }

    /**
     * get l intitule de la formation
     *
     * @return l intitule de la formation
     */
    public String getIntituleformation() {
        return intituleformation;
    }

    /**
     * set l'intitule de la formation 
     * @param intituleformation l intitule de la formation
     */
    public void setIntituleformation(String intituleformation) {
        this.intituleformation = intituleformation;
    }

    /**
     * get le code client
     * @return  code client
     */
    public Integer getCodeclient() {
        return codeclient;
    }

    /**
     * set le code client 
     * @param codeclient code client
     */
    public void setCodeclient(Integer codeclient) {
        this.codeclient = codeclient;
    }

    /**
     * get le nombre de personne dans la formation 
     * @return  le nombre de personne dans la formation 
     */
    public Integer getNbpersonne() {
        return nbpersonne;
    }

    /**
     * set le nombre de personne dans la formation
     * @param nbpersonne le nombre de personne dans la formation 
     */
    public void setNbpersonne(Integer nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    /**
     * get le nom du client 
     * @return le nom du client 
     */
    public String getNomclient() {
        return nomclient;
    }

    /**
     * set le nom du client 
     * @param nomclient le nom du client 
     */
    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    @XmlTransient
    /**
     * get Formation compose collection 
     */
    public Collection<Formationcompose> getFormationcomposeCollection() {
        return formationcomposeCollection;
    }

    /**
     * set formation compte collection 
     * @param formationcomposeCollection   formation compte collection 
     */
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
