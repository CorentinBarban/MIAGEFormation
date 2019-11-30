/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Corentin
 */
public class SallesDTO implements Serializable{
    
    private List<SalleDTO> listeSalle;

    public SallesDTO() {
    }

    public List<SalleDTO> getListeSalle() {
        return listeSalle;
    }

    public void setListeSalle(List<SalleDTO> listeSalle) {
        this.listeSalle = listeSalle;
    }

    @Override
    public String toString() {
        return "SallesDTO{" + "listeSalle=" + listeSalle + '}';
    }
    
    
    
    
}
