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

    /**
     * Constructeur formation compose à partion formation key et demande formation key
     * @param formationkey formation key formation
     * @param demandeformationkey demande formation key
     */
    public FormationcomposePK(int formationkey, int demandeformationkey) {
        this.formationkey = formationkey;
        this.demandeformationkey = demandeformationkey;
    }

    /**
     * get la key de la formation 
     * @return key de la formation 
     */
    public int getFormationkey() {
        return formationkey;
    }

    /**
     * set  la key de la formation 
     * @param formationkey key de la formation 
     */
    public void setFormationkey(int formationkey) {
        this.formationkey = formationkey;
    }

    /**
     * get la key de demande formation
     * @return key de demande formation 
     */
    public int getDemandeformationkey() {
        return demandeformationkey;
    }

    /**
     * set la key de demande formation
     * @param demandeformationkey key de demande formation 
     */
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
