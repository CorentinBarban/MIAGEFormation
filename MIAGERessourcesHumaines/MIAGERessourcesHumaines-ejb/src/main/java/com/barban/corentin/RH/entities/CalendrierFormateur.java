/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.entities;

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
@Table(name = "CALENDRIER_FORMATEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalendrierFormateur.findAll", query = "SELECT c FROM CalendrierFormateur c")
    , @NamedQuery(name = "CalendrierFormateur.findByCalendrierkey", query = "SELECT c FROM CalendrierFormateur c WHERE c.calendrierFormateurPK.calendrierkey = :calendrierkey")
    , @NamedQuery(name = "CalendrierFormateur.findByFormateurkey", query = "SELECT c FROM CalendrierFormateur c WHERE c.calendrierFormateurPK.formateurkey = :formateurkey")
    , @NamedQuery(name = "CalendrierFormateur.findByStatut", query = "SELECT c FROM CalendrierFormateur c WHERE c.statut = :statut")})
public class CalendrierFormateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalendrierFormateurPK calendrierFormateurPK;
    @Size(max = 50)
    @Column(name = "STATUT")
    private String statut;
    @JoinColumn(name = "CALENDRIERKEY", referencedColumnName = "IDCALENDRIER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Calendrier calendrier;
    @JoinColumn(name = "FORMATEURKEY", referencedColumnName = "IDFORMATEUR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Formateur formateur;

    public CalendrierFormateur() {
    }

    /**
     * constructeur du calendrier formateur à partir de la primary key
     *
     * @param calendrierFormateurPK calendrier formateur primary key
     */
    public CalendrierFormateur(CalendrierFormateurPK calendrierFormateurPK) {
        this.calendrierFormateurPK = calendrierFormateurPK;
    }

    /**
     * constructeur calendrier formateur à partir de la calendrier key et
     * formateur key
     *
     * @param calendrierkey calendrier key 
     * @param formateurkey formateur key
     */
    public CalendrierFormateur(int calendrierkey, int formateurkey) {
        this.calendrierFormateurPK = new CalendrierFormateurPK(calendrierkey, formateurkey);
    }

    /**
     * get la primary key du calendrier formateur
     *
     * @return la primary key du calendrier formateur
     */
    public CalendrierFormateurPK getCalendrierFormateurPK() {
        return calendrierFormateurPK;
    }

    /**
     * set la primary ky du calendrier formateur
     *
     * @param calendrierFormateurPK la primary key du calendrier formateur
     */
    public void setCalendrierFormateurPK(CalendrierFormateurPK calendrierFormateurPK) {
        this.calendrierFormateurPK = calendrierFormateurPK;
    }

    /**
     * get statut
     *
     * @return statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * set statut
     *
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
     * get formateur
     * @return formateur
     */
    public Formateur getFormateur() {
        return formateur;
    }

    /**
     * set formateur 
     * @param formateur formateur
     */
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calendrierFormateurPK != null ? calendrierFormateurPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendrierFormateur)) {
            return false;
        }
        CalendrierFormateur other = (CalendrierFormateur) object;
        if ((this.calendrierFormateurPK == null && other.calendrierFormateurPK != null) || (this.calendrierFormateurPK != null && !this.calendrierFormateurPK.equals(other.calendrierFormateurPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.RH.entities.CalendrierFormateur[ calendrierFormateurPK=" + calendrierFormateurPK + " ]";
    }

}
