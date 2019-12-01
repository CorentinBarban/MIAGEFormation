/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FORMATIONCOMPOSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formationcompose.findAll", query = "SELECT f FROM Formationcompose f")
    , @NamedQuery(name = "Formationcompose.findByDemandeformationkey", query = "SELECT f FROM Formationcompose f WHERE f.formationcomposePK.demandeformationkey = :demandeformationkey")
    , @NamedQuery(name = "Formationcompose.findByFormationkey", query = "SELECT f FROM Formationcompose f WHERE f.formationcomposePK.formationkey = :formationkey")
    , @NamedQuery(name = "Formationcompose.findByDateformation", query = "SELECT f FROM Formationcompose f WHERE f.dateformation = :dateformation")
    , @NamedQuery(name = "Formationcompose.findByKeysalle", query = "SELECT f FROM Formationcompose f WHERE f.keysalle = :keysalle")
    , @NamedQuery(name = "Formationcompose.findByKeyformateur", query = "SELECT f FROM Formationcompose f WHERE f.keyformateur = :keyformateur")
    , @NamedQuery(name = "Formationcompose.findByStatut", query = "SELECT f FROM Formationcompose f WHERE f.statut = :statut")
    , @NamedQuery(name = "Formationcompose.findByNbpersonne", query = "SELECT f FROM Formationcompose f WHERE f.nbpersonne = :nbpersonne")})
public class Formationcompose implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormationcomposePK formationcomposePK;
    @Column(name = "DATEFORMATION")
    @Temporal(TemporalType.DATE)
    private Date dateformation;
    @Column(name = "KEYSALLE")
    private Integer keysalle;
    @Column(name = "KEYFORMATEUR")
    private Integer keyformateur;
    @Size(max = 100)
    @Column(name = "STATUT")
    private String statut;
    @Column(name = "NBPERSONNE")
    private Integer nbpersonne;
    @JoinColumn(name = "FORMATIONKEY", referencedColumnName = "IDFORMATION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Formation formation;
    @JoinColumn(name = "DEMANDEFORMATIONKEY", referencedColumnName = "IDDEMANDEFORMATION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stockagedemandeformation stockagedemandeformation;

    public Formationcompose() {
    }

    public Formationcompose(FormationcomposePK formationcomposePK) {
        this.formationcomposePK = formationcomposePK;
    }

    public Formationcompose(int demandeformationkey, int formationkey) {
        this.formationcomposePK = new FormationcomposePK(demandeformationkey, formationkey);
    }

    public FormationcomposePK getFormationcomposePK() {
        return formationcomposePK;
    }

    public void setFormationcomposePK(FormationcomposePK formationcomposePK) {
        this.formationcomposePK = formationcomposePK;
    }

    public Date getDateformation() {
        return dateformation;
    }

    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(Integer nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Stockagedemandeformation getStockagedemandeformation() {
        return stockagedemandeformation;
    }

    public void setStockagedemandeformation(Stockagedemandeformation stockagedemandeformation) {
        this.stockagedemandeformation = stockagedemandeformation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formationcomposePK != null ? formationcomposePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formationcompose)) {
            return false;
        }
        Formationcompose other = (Formationcompose) object;
        if ((this.formationcomposePK == null && other.formationcomposePK != null) || (this.formationcomposePK != null && !this.formationcomposePK.equals(other.formationcomposePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.formation.entities.Formationcompose[ formationcomposePK=" + formationcomposePK + " ]";
    }
    
}
