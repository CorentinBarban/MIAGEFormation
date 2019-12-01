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
    @Column(name = "FORMATIONKEY")
    private int formationkey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEMANDEFORMATIONKEY")
    private int demandeformationkey;

    public FormationcomposePK() {
    }

    public FormationcomposePK(int formationkey, int demandeformationkey) {
        this.formationkey = formationkey;
        this.demandeformationkey = demandeformationkey;
    }

    public int getFormationkey() {
        return formationkey;
    }

    public void setFormationkey(int formationkey) {
        this.formationkey = formationkey;
    }

    public int getDemandeformationkey() {
        return demandeformationkey;
    }

    public void setDemandeformationkey(int demandeformationkey) {
        this.demandeformationkey = demandeformationkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) formationkey;
        hash += (int) demandeformationkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormationcomposePK)) {
            return false;
        }
        FormationcomposePK other = (FormationcomposePK) object;
        if (this.formationkey != other.formationkey) {
            return false;
        }
        if (this.demandeformationkey != other.demandeformationkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.formation.entities.FormationcomposePK[ formationkey=" + formationkey + ", demandeformationkey=" + demandeformationkey + " ]";
    }
    
}
