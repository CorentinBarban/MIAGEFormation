/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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

    public FormationcomposePK() {
    }

    public FormationcomposePK(int demandeformationkey, int formationkey) {
        this.demandeformationkey = demandeformationkey;
        this.formationkey = formationkey;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) demandeformationkey;
        hash += (int) formationkey;
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
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.formation.entities.FormationcomposePK[ demandeformationkey=" + demandeformationkey + ", formationkey=" + formationkey + " ]";
    }
    
}
