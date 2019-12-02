/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    , @NamedQuery(name = "Formationcompose.findByFormationkey", query = "SELECT f FROM Formationcompose f WHERE f.formationcomposePK.formationkey = :formationkey")
    , @NamedQuery(name = "Formationcompose.findByDemandeformationkey", query = "SELECT f FROM Formationcompose f WHERE f.formationcomposePK.demandeformationkey = :demandeformationkey")
    , @NamedQuery(name = "Formationcompose.findByNbparticipants", query = "SELECT f FROM Formationcompose f WHERE f.nbparticipants = :nbparticipants")
    , @NamedQuery(name = "Formationcompose.sumNbparticipant", query = "SELECT sum(f.nbparticipants), f.formationcomposePK.formationkey FROM Formationcompose f GROUP BY f.formationcomposePK.formationkey")})
public class Formationcompose implements Serializable { 

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormationcomposePK formationcomposePK;
    @Column(name = "NBPARTICIPANTS")
    private Integer nbparticipants;
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

    public Formationcompose(int formationkey, int demandeformationkey) {
        this.formationcomposePK = new FormationcomposePK(formationkey, demandeformationkey);
    }

    public FormationcomposePK getFormationcomposePK() {
        return formationcomposePK;
    }

    public void setFormationcomposePK(FormationcomposePK formationcomposePK) {
        this.formationcomposePK = formationcomposePK;
    }

    public Integer getNbparticipants() {
        return nbparticipants;
    }

    public void setNbparticipants(Integer nbparticipants) {
        this.nbparticipants = nbparticipants;
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
        return "Formationcompose{" + "formationcomposePK=" + formationcomposePK + ", nbparticipants=" + nbparticipants + ", formation=" + formation + ", stockagedemandeformation=" + stockagedemandeformation + '}';
    }
    
    
}
