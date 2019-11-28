 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.services;

import javax.ejb.Local;

/**
 *
 * @author julie
 */
@Local
public interface ServiceRHLocal {
    
    boolean verifierExistenceFormateur(Integer idFormateur);
    
}
