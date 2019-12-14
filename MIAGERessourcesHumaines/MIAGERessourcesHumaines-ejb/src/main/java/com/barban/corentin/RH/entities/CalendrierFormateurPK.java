/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.entities;

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
public class CalendrierFormateurPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CALENDRIERKEY")
    private int calendrierkey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMATEURKEY")
    private int formateurkey;

    public CalendrierFormateurPK() {
    }

    /**
     * constrcuteur calendrier formateur pk en focntion de calendrier key et formateur key
     * @param calendrierkey calendrier key
     * @param formateurkey formateur key
     */
    public CalendrierFormateurPK(int calendrierkey, int formateurkey) {
        this.calendrierkey = calendrierkey;
        this.formateurkey = formateurkey;
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
     * get formateur key
     * @return  formateur key
     */
    public int getFormateurkey() {
        return formateurkey;
    }

    /**
     * set formateur key
     * @param formateurkey formateur key
     */
    public void setFormateurkey(int formateurkey) {
        this.formateurkey = formateurkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) calendrierkey;
        hash += (int) formateurkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendrierFormateurPK)) {
            return false;
        }
        CalendrierFormateurPK other = (CalendrierFormateurPK) object;
        if (this.calendrierkey != other.calendrierkey) {
            return false;
        }
        if (this.formateurkey != other.formateurkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.RH.entities.CalendrierFormateurPK[ calendrierkey=" + calendrierkey + ", formateurkey=" + formateurkey + " ]";
    }
    
}
