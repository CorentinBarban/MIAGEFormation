/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.entities;

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
public class CalendrierSallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CALENDRIERKEY")
    private int calendrierkey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALLEKEY")
    private int sallekey;

    public CalendrierSallePK() {
    }

    /**
     * constructeur de calendrier salle primary key à partir de key calendrier et salle key 
     * @param calendrierkey calendrier key
     * @param sallekey salle key 
     */
    public CalendrierSallePK(int calendrierkey, int sallekey) {
        this.calendrierkey = calendrierkey;
        this.sallekey = sallekey;
    }

    /**
     * get calendrier key 
     * @return calendrier key 
     */
    public int getCalendrierkey() {
        return calendrierkey;
    }

    /**
     * set calendrier key
     * @param calendrierkey calendrier key 
     */
    public void setCalendrierkey(int calendrierkey) {
        this.calendrierkey = calendrierkey;
    }

    /**
     * get salle key
     * @return salle key
     */
    public int getSallekey() {
        return sallekey;
    }

    /**
     * set salle key 
     * @param sallekey salle key
     */
    public void setSallekey(int sallekey) {
        this.sallekey = sallekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) calendrierkey;
        hash += (int) sallekey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendrierSallePK)) {
            return false;
        }
        CalendrierSallePK other = (CalendrierSallePK) object;
        if (this.calendrierkey != other.calendrierkey) {
            return false;
        }
        if (this.sallekey != other.sallekey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.patrimoine.entities.CalendrierSallePK[ calendrierkey=" + calendrierkey + ", sallekey=" + sallekey + " ]";
    }
    
}
