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

    public Integer getIdCompetence() {
        return idCompetence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<FormateurDTO> getFormateurCollection() {
        return formateurCollection;
    }

    public void setFormateurCollection(Collection<FormateurDTO> formateurCollection) {
        this.formateurCollection = formateurCollection;
    }

    @Override
    public String toString() {
        return "CompetencetDTO{" + "idCompetence=" + idCompetence + ", nom=" + nom + 
                ", formateurCollection=" + formateurCollection + '}';
    }

}
