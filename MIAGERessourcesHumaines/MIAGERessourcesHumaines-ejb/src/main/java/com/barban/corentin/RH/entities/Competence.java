/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "COMPETENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c")
    , @NamedQuery(name = "Competence.findByIdcompetence", query = "SELECT c FROM Competence c WHERE c.idcompetence = :idcompetence")
    , @NamedQuery(name = "Competence.findByNomcompetence", query = "SELECT c FROM Competence c WHERE c.nomcompetence = :nomcompetence")})
public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOMPETENCE")
    private Integer idcompetence;
    @Size(max = 100)
    @Column(name = "NOMCOMPETENCE")
    private String nomcompetence;
    @ManyToMany(mappedBy = "competenceCollection")
    private Collection<Formateur> formateurCollection;

    public Competence() {
    }

    /**
     * constructeur competence à partir de l'id competence
     *
     * @param idcompetence id competence
     */
    public Competence(Integer idcompetence) {
        this.idcompetence = idcompetence;
    }

    /**
     * get id competence
     *
     * @return id competence
     */
    public Integer getIdcompetence() {
        return idcompetence;
    }

    /**
     * set id competence
     *
     * @param idcompetence id competence
     */
    public void setIdcompetence(Integer idcompetence) {
        this.idcompetence = idcompetence;
    }

    /**
     * get nom competence
     *
     * @return  nom competence
     */
    public String getNomcompetence() {
        return nomcompetence;
    }

    /**
     * set nom competence
     *
     * @param nomcompetence  nom competence
     */
    public void setNomcompetence(String nomcompetence) {
        this.nomcompetence = nomcompetence;
    }

    /**
     * get la liste des formateurs
     *
     * @return la liste des formateurs
     */
    @XmlTransient
    public Collection<Formateur> getFormateurCollection() {
        return formateurCollection;
    }

    /**
     * set la liste des formateurs
     *
     * @param formateurCollection la liste des formateurs
     */
    public void setFormateurCollection(Collection<Formateur> formateurCollection) {
        this.formateurCollection = formateurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompetence != null ? idcompetence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.idcompetence == null && other.idcompetence != null) || (this.idcompetence != null && !this.idcompetence.equals(other.idcompetence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.RH.entities.Competence[ idcompetence=" + idcompetence + " ]";
    }

}
