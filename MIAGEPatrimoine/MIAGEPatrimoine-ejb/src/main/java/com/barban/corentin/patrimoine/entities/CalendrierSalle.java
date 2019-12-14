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

    /**
     * constructer du calendrier salle à partir de la primary key 
     * @param calendrierSallePK calendrier salle primary key 
     */
    public CalendrierSalle(CalendrierSallePK calendrierSallePK) {
        this.calendrierSallePK = calendrierSallePK;
    }

    /**
     * constructeur du calendrier salle à partir de la key calendrier et de la key salle
     * @param calendrierkey key calendrier  
     * @param sallekey key salle
     */
    public CalendrierSalle(int calendrierkey, int sallekey) {
        this.calendrierSallePK = new CalendrierSallePK(calendrierkey, sallekey);
    }

    /**
     * get calendrier salle primary key 
     * @return calendrier salle primary key 
     */
    public CalendrierSallePK getCalendrierSallePK() {
        return calendrierSallePK;
    }

    /**
     * set calendrier salle primary key 
     * @param calendrierSallePK calendrier salle primary key 
     */
    public void setCalendrierSallePK(CalendrierSallePK calendrierSallePK) {
        this.calendrierSallePK = calendrierSallePK;
    }

    /**
     * get statut
     * @return statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * set statut
     * @param statut statut
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * get calendrier 
     * @return calendrier
     */
    public Calendrier getCalendrier() {
        return calendrier;
    }

    /** 
     * set calendrier 
     * @param calendrier calendrier
     */
    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    /**
     * get salle 
     * @return salle
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     * set salle
     * @param salle salle
     */
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
        return "com.barban.corentin.patrimoine.entities.CalendrierSalle[ calendrierSallePK=" + calendrierSallePK + " ]";
    }
    
}
