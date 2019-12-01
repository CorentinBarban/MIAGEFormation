/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Corentin
 */
public class SallesDTO implements Serializable{
    
    private HashMap<SalleDTO,List<Date>> HashMapDateSalle;

    public SallesDTO() {
    }

    public HashMap<SalleDTO, List<Date>> getHashMapDateSalle() {
        return HashMapDateSalle;
    }

    public void setHashMapDateSalle(HashMap<SalleDTO, List<Date>> HashMapDateSalle) {
        this.HashMapDateSalle = HashMapDateSalle;
    }

    @Override
    public String toString() {
        return "SallesDTO{" + "HashMapDateSalle=" + HashMapDateSalle + '}';
    }

   
    
}
