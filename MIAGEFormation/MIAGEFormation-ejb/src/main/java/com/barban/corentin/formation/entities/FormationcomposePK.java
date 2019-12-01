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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Corentin
 */
@Embeddable
public class FormationcomposePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DEMANDEFORMATIONKEY")
    private int demandeformationkey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMATIONKEY")
    private int formationkey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEFORMATION")
    @Temporal(TemporalType.DATE)
    private Date dateformation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEYSALLE")
    private int keysalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEYFORMATEUR")
    private int keyformateur;

    public FormationcomposePK() {
    }

    public FormationcomposePK(int demandeformationkey, int formationkey, Date dateformation, int keysalle, int keyformateur) {
        this.demandeformationkey = demandeformationkey;
        this.formationkey = formationkey;
        this.dateformation = dateformation;
        this.keysalle = keysalle;
        this.keyformateur = keyformateur;
    }

    public int getDemandeformationkey() {
        return demandeformationkey;
    }

    public void setDemandeformationkey(int demandeformationkey) {
        this.demandeformationkey = demandeformationkey;
    }

    public int getFormationkey() {
        return formationkey;
    }

    public void setFormationkey(int formationkey) {
        this.formationkey = formationkey;
    }

    public Date getDateformation() {
        return dateformation;
    }

    public void setDateformation(Date dateformation) {
        this.dateformation = dateformation;
    }

    public int getKeysalle() {
        return keysalle;
    }

    public void setKeysalle(int keysalle) {
        this.keysalle = keysalle;
    }

    public int getKeyformateur() {
        return keyformateur;
    }

    public void setKeyformateur(int keyformateur) {
        this.keyformateur = keyformateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) demandeformationkey;
        hash += (int) formationkey;
        hash += (dateformation != null ? dateformation.hashCode() : 0);
        hash += (int) keysalle;
        hash += (int) keyformateur;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormationcomposePK)) {
            return false;
        }
        FormationcomposePK other = (FormationcomposePK) object;
        if (this.demandeformationkey != other.demandeformationkey) {
            return false;
        }
        if (this.formationkey != other.formationkey) {
            return false;
        }
        if ((this.dateformation == null && other.dateformation != null) || (this.dateformation != null && !this.dateformation.equals(other.dateformation))) {
            return false;
        }
        if (this.keysalle != other.keysalle) {
            return false;
        }
        if (this.keyformateur != other.keyformateur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.formation.entities.FormationcomposePK[ demandeformationkey=" + demandeformationkey + ", formationkey=" + formationkey + ", dateformation=" + dateformation + ", keysalle=" + keysalle + ", keyformateur=" + keyformateur + " ]";
    }
    
}
