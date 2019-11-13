/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "CALENDRIER_SALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalendrierSalle.findAll", query = "SELECT c FROM CalendrierSalle c")
    , @NamedQuery(name = "CalendrierSalle.findByCalendrierkey", query = "SELECT c FROM CalendrierSalle c WHERE c.calendrierSallePK.calendrierkey = :calendrierkey")
    , @NamedQuery(name = "CalendrierSalle.findBySallekey", query = "SELECT c FROM CalendrierSalle c WHERE c.calendrierSallePK.sallekey = :sallekey")
    , @NamedQuery(name = "CalendrierSalle.findByStatut", query = "SELECT c FROM CalendrierSalle c WHERE c.statut = :statut")})
public class CalendrierSalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalendrierSallePK calendrierSallePK;
    @Size(max = 50)
    @Column(name = "STATUT")
    private String statut;
    @JoinColumn(name = "CALENDRIERKEY", referencedColumnName = "IDCALENDRIER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Calendrier calendrier;
    @JoinColumn(name = "SALLEKEY", referencedColumnName = "IDSALLE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Salle salle;

    public CalendrierSalle() {
    }

    public CalendrierSalle(CalendrierSallePK calendrierSallePK) {
        this.calendrierSallePK = calendrierSallePK;
    }

    public CalendrierSalle(int calendrierkey, int sallekey) {
        this.calendrierSallePK = new CalendrierSallePK(calendrierkey, sallekey);
    }

    public CalendrierSallePK getCalendrierSallePK() {
        return calendrierSallePK;
    }

    public void setCalendrierSallePK(CalendrierSallePK calendrierSallePK) {
        this.calendrierSallePK = calendrierSallePK;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calendrierSallePK != null ? calendrierSallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendrierSalle)) {
            return false;
        }
        CalendrierSalle other = (CalendrierSalle) object;
        if ((this.calendrierSallePK == null && other.calendrierSallePK != null) || (this.calendrierSallePK != null && !this.calendrierSallePK.equals(other.calendrierSallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.entities.CalendrierSalle[ calendrierSallePK=" + calendrierSallePK + " ]";
    }
    
}
