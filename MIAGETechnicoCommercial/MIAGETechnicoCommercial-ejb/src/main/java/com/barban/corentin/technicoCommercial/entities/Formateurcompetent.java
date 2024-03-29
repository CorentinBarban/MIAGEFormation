/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.entities;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corentin
 */
@Entity
@Table(name = "FORMATEURCOMPETENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formateurcompetent.findAll", query = "SELECT f FROM Formateurcompetent f")
    , @NamedQuery(name = "Formateurcompetent.findByIdformateurcompetent", query = "SELECT f FROM Formateurcompetent f WHERE f.idformateurcompetent = :idformateurcompetent")
    , @NamedQuery(name = "Formateurcompetent.findByFormateurkey", query = "SELECT f FROM Formateurcompetent f WHERE f.formateurkey = :formateurkey")})
public class Formateurcompetent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFORMATEURCOMPETENT")
    private Integer idformateurcompetent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMATEURKEY")
    private int formateurkey;
    @ManyToMany(mappedBy = "formateurcompetentCollection")
    private Collection<Formationcatalogue> formationcatalogueCollection;

    public Formateurcompetent() {
    }

    /**
     * constructeur formateur competent à partir du formateur key 
     * @param formateurkey key formateur 
     */
    public Formateurcompetent( int formateurkey) {
        this.formateurkey = formateurkey;
    }
    
    /**
     * constructeur formateur competent à partir de l'id formateur competent
     * @param idformateurcompetent  id formateur competent
     */
    public Formateurcompetent(Integer idformateurcompetent) {
        this.idformateurcompetent = idformateurcompetent;
    }

    /**
     * onstructeur formateur competent à partir de l'id formateur competent et formateur key 
     * @param idformateurcompetent  id formateur competent
     * @param formateurkey key formateur
     */
    public Formateurcompetent(Integer idformateurcompetent, int formateurkey) {
        this.idformateurcompetent = idformateurcompetent;
        this.formateurkey = formateurkey;
    }

    /**
     * get id formateur competent 
     * @return id formateur competent 
     */
    public Integer getIdformateurcompetent() {
        return idformateurcompetent;
    }

    /**
     * set id formateur competent
     * @param idformateurcompetent id formateur competent 
     */
    public void setIdformateurcompetent(Integer idformateurcompetent) {
        this.idformateurcompetent = idformateurcompetent;
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
     * @param formateurkey  formateur key
     */
    public void setFormateurkey(int formateurkey) {
        this.formateurkey = formateurkey;
    }

    /**
     * get la collection de formation du catalogue
     * @return la collection de formation du catalogue
     */
    @XmlTransient
    public Collection<Formationcatalogue> getFormationcatalogueCollection() {
        return formationcatalogueCollection;
    }

    /**
     * set  la collection de formation du catalogue
     * @param formationcatalogueCollection la collection de formation du catalogue
     */
    public void setFormationcatalogueCollection(Collection<Formationcatalogue> formationcatalogueCollection) {
        this.formationcatalogueCollection = formationcatalogueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformateurcompetent != null ? idformateurcompetent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formateurcompetent)) {
            return false;
        }
        Formateurcompetent other = (Formateurcompetent) object;
        if ((this.idformateurcompetent == null && other.idformateurcompetent != null) || (this.idformateurcompetent != null && !this.idformateurcompetent.equals(other.idformateurcompetent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barban.corentin.entities.Formateurcompetent[ idformateurcompetent=" + idformateurcompetent + " ]";
    }
    
}
