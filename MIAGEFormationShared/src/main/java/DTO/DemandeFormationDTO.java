/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author julie
 */
public class DemandeFormationDTO implements Serializable {

    private Date date;
    private String codeFormation;
    private int codeClient;

    public DemandeFormationDTO() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    @Override
    public String toString() {
        return "DemandeFormationDTO{" + "date=" + date + ", codeFormation=" + codeFormation + ", codeClient=" + codeClient + '}';
    }

}
