/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author julie
 */
public class FormateurDTO implements Serializable {

    private int idFormateur;
    private String nom;
    private String prenom;
    private Collection<CompetenceDTO> competenceCollection;
    private Date date;
    private String statut;

    /**
     * get id du formateur
     *
     * @return id du formateur
     */
    public int getIdFormateur() {
        return idFormateur;
    }

    /**
     * get nom du formateur
     *
     * @return nom du formateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * get prenom du formateur
     *
     * @return prenom du formateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * set nom du formateur
     *
     * @param nom nom du formateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * set prenom du formateur
     *
     * @param prenom prenom du formateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * set id du formateur
     *
     * @param idFormateur id du formateur
     */
    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    /**
     * get les competence du formateur
     *
     * @return les competence du formateur
     */
    public Collection<CompetenceDTO> getCompetenceCollection() {
        return competenceCollection;
    }

    /**
     * set les competences du formateur
     *
     * @param competenceCollection les competences du formateur
     */
    public void setCompetenceCollection(Collection<CompetenceDTO> competenceCollection) {
        this.competenceCollection = competenceCollection;
    }

    /**
     * get date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * set date
     *
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * get status du formateur
     *
     * @return status du formateur
     */
    public String getStatut() {
        return statut;
    }

    /**
     * set status du formateur
     *
     * @param statut status du formateur
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "FormateurDTO{" + "idFormateur=" + idFormateur + ", nom=" + nom + ", prenom=" + prenom + ", competenceCollection=" + competenceCollection + ", date=" + date + ", statut=" + statut + '}';
    }

}
