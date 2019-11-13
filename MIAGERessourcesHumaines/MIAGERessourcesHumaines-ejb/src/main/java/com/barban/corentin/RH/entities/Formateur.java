/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "FORMATEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formateur.findAll", query = "SELECT f FROM Formateur f")
    , @NamedQuery(name = "Formateur.findByIdformateur", query = "SELECT f FROM Formateur f WHERE f.idformateur = :idformateur")
    , @NamedQuery(name = "Formateur.findByNom", query = "SELECT f FROM Formateur f WHERE f.nom = :nom")
    , @NamedQuery(name = "Formateur.findByPrenom", query = "SELECT f FROM Formateur f WHERE f.prenom = :prenom")})
public class Formateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFORMATEUR")
    private Integer idformateur;
    @Size(max = 100)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 100)
    @Column(name = "PRENOM")
    private String prenom;
    @JoinTable(name = "COMPETENCE_FORMATEUR", joinColumns = {
        @JoinColumn(name = "FORMATEURKEY", referencedColumnName = "IDFORMATEUR")}, inverseJoinColumns = {
        @JoinColumn(name = "COMPETENCEKEY", referencedColumnName = "IDCOMPETENCE")})
    @ManyToMany
    private Collection<Competence> competenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formateur")
    private Collection<CalendrierFormateur> calendrierFormateurCollection;

    public Formateur() {
    }

    public Formateur(Integer idformateur) {
        this.idformateur = idformateur;
    }

    public Integer getIdformateur() {
        return idformateur;
    }

    public void setIdformateur(Integer idformateur) {
        this.idformateur = idformateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlTransient
    public Collection<Competence> getCompetenceCollection() {
        return competenceCollection;
    }

    public void setCompetenceCollection(Collection<Competence> competenceCollection) {
        this.competenceCollection = competenceCollection;
    }

    @XmlTransient
    public Collection<CalendrierFormateur> getCalendrierFormateurCollection() {
        return calendrierFormateurCollection;
    }

    public void setCalendrierFormateurCollection(Collection<CalendrierFormateur> calendrierFormateurCollection) {
        this.calendrierFormateurCollection = calendrierFormateurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformateur != null ? idformateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formateur)) {
            return false;
        }
        Formateur other = (Formateur) object;
        if ((this.idformateur == null && other.idformateur != null) || (this.idformateur != null && !this.idformateur.equals(other.idformateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.RH.entities.Formateur[ idformateur=" + idformateur + " ]";
    }
    
}
