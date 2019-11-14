/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.entities;

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
    private Collection<CalendrierSalle> calendrierSalleCollection;

    public Calendrier() {
    }

    public Calendrier(Integer idcalendrier) {
        this.idcalendrier = idcalendrier;
    }

    public Integer getIdcalendrier() {
        return idcalendrier;
    }

    public void setIdcalendrier(Integer idcalendrier) {
        this.idcalendrier = idcalendrier;
    }

    public Date getDatecalendrier() {
        return datecalendrier;
    }

    public void setDatecalendrier(Date datecalendrier) {
        this.datecalendrier = datecalendrier;
    }

    @XmlTransient
    public Collection<CalendrierSalle> getCalendrierSalleCollection() {
        return calendrierSalleCollection;
    }

    public void setCalendrierSalleCollection(Collection<CalendrierSalle> calendrierSalleCollection) {
        this.calendrierSalleCollection = calendrierSalleCollection;
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
        return "com.barban.corentin.patrimoine.entities.Calendrier[ idcalendrier=" + idcalendrier + " ]";
    }
    
}
