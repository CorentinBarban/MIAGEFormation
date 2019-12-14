/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Collection;

/**
 *
 * @author julie
 */
public class CompetenceDTO {

    private Integer idCompetence;
    private String nom;
    private Collection<FormateurDTO> formateurCollection;

    /**
     * get id de la competence
     *
     * @return id de la competence
     */
    public Integer getIdCompetence() {
        return idCompetence;
    }

    /**
     * get nom de la compétence
     *
     * @return nom de la compétence
     */
    public String getNom() {
        return nom;
    }

    /**
     * set nom de la compétence
     * @param nom nom de la compétence
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get les formateurs de cette compétence
     * @return collection de formateur
     */
    public Collection<FormateurDTO> getFormateurCollection() {
        return formateurCollection;
    }

    /**
     * set les formateurs de cette compétence
     * @param formateurCollection collection de formateur
     */
    public void setFormateurCollection(Collection<FormateurDTO> formateurCollection) {
        this.formateurCollection = formateurCollection;
    }

    @Override
    public String toString() {
        return "CompetencetDTO{" + "idCompetence=" + idCompetence + ", nom=" + nom
                + ", formateurCollection=" + formateurCollection + '}';
    }

}
