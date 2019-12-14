/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "CALENDRIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendrier.findAll", query = "SELECT c FROM Calendrier c")
    , @NamedQuery(name = "Calendrier.findByIdcalendrier", query = "SELECT c FROM Calendrier c WHERE c.idcalendrier = :idcalendrier")
    , @NamedQuery(name = "Calendrier.findByDatecalendrier", query = "SELECT c FROM Calendrier c WHERE c.datecalendrier = :datecalendrier")})
public class Calendrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCALENDRIER")
    private Integer idcalendrier;
    @Column(name = "DATECALENDRIER")
    @Temporal(TemporalType.DATE)
    private Date datecalendrier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendrier")
    private Collection<CalendrierFormateur> calendrierFormateurCollection;

    public Calendrier() {
    }

    /**
     * constructeur calendrier à partir de son id
     *
     * @param idcalendrier id caldendrier
     */
    public Calendrier(Integer idcalendrier) {
        this.idcalendrier = idcalendrier;
    }

    /**
     * get id du calendrier
     *
     * @return id du calendrier
     */
    public Integer getIdcalendrier() {
        return idcalendrier;
    }

    /**
     * set id du calendrier
     *
     * @param idcalendrier id du calendrier
     */
    public void setIdcalendrier(Integer idcalendrier) {
        this.idcalendrier = idcalendrier;
    }

    /**
     * get date du calendrier
     *
     * @return date du calendrier
     */
    public Date getDatecalendrier() {
        return datecalendrier;
    }

    /**
     * set date du calendrier
     *
     * @param datecalendrier date du calendrier
     */
    public void setDatecalendrier(Date datecalendrier) {
        this.datecalendrier = datecalendrier;
    }

    /**
     * get la liste des calendriers formateur
     *
     * @return la liste des calendriers formateur
     */
    @XmlTransient
    public Collection<CalendrierFormateur> getCalendrierFormateurCollection() {
        return calendrierFormateurCollection;
    }

    /**
     * set la liste des calendriers formateur
     *
     * @param calendrierFormateurCollection la liste des calendriers formateur
     */
    public void setCalendrierFormateurCollection(Collection<CalendrierFormateur> calendrierFormateurCollection) {
        this.calendrierFormateurCollection = calendrierFormateurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalendrier != null ? idcalendrier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendrier)) {
            return false;
        }
        Calendrier other = (Calendrier) object;
        if ((this.idcalendrier == null && other.idcalendrier != null) || (this.idcalendrier != null && !this.idcalendrier.equals(other.idcalendrier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.RH.entities.Calendrier[ idcalendrier=" + idcalendrier + " ]";
    }

}
